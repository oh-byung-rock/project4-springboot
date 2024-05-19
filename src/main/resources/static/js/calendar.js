const sections = {
    "100원": 0,
    "200원": 72,
    "300원": 144,
    "400원": 216,
    "500원": 288
};

function checkAttendanceAndSpin() {
    fetch(`/test/attendance/${memberId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('test result isis:', data);
            if (data.length === 0) {
                spin();  // data.length가 0인 경우에만 spin 함수 호출
            } else {
                alert('하루 1회만 가능합니다');
            }
        })
        .catch(error => console.error('Error fetching attendance data:', error));
}

function spin() {
    const today = moment().format('YYYY-MM-DD');
    const roulette = document.getElementById('roulette');
    const randomRotation = 3600; // 10바퀴 + 0~360도 랜덤 회전

    fetch('/spin')
        .then(response => response.json())
        .then(data => {
            const prizeRotation = sections[data.result];
            const finalRotation = randomRotation + prizeRotation;
            console.log('prize', prizeRotation);
            roulette.style.transform = `rotate(${finalRotation}deg)`;

            setTimeout(() => {
                const data2 = { memberId: memberId, date: today, status: '8975' };
                fetch('/attendance', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data2)
                })
                .then(response => response.json())
                .then(result => {
                    console.log('data is', data2);
                    document.getElementById('result').innerText = "결과: " + data.result + " 당첨";
                    $('#calendar').fullCalendar('refetchEvents');
                })
                .catch(error => console.error('Error marking attendance:', error));
            }, 2000); // 2초 후 결과 표시
        })
        .catch(error => console.error('Error spinning the roulette:', error));
}

$(document).ready(function() {
    $('#calendar').fullCalendar({
        header: {
            left: 'prev',
            center: 'title',
            right: 'next'
        },
        defaultView: 'month',
        editable: false,
        height: 'auto', // 스크롤바 안생기게 자동 높이 조정
        events: function(start, end, timezone, callback) {
            fetch(`/test/attendance/${memberId}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log('test result isis:', data);
                    const events = data.map(attendance => ({
                        title: attendance.status,
                        start: attendance.date,
                        color: attendance.status === '8975' ? 'green' : 'red'
                    }));
                    callback(events);
                })
                .catch(error => console.error('Error fetching attendance data:', error));
        }
    });
//    $('.start').off('click', spin); 룰렛 자동 동작
});