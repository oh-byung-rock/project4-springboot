package jpacalendarchallenge.jpacacha.exception;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException() {
    }
    public NotEnoughStockException(String message) {
        super(message);
    } // RuntimeException.class의 기능을 사용하기위해 super로 접근한것이고
    // message를 출력하진않고 message 내용을 저장하고있는 상태, 추후 확인하고싶으면 getMessage() 쓰면됨
    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }
}
