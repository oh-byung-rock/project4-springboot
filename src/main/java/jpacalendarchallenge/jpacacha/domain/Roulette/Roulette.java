package jpacalendarchallenge.jpacacha.domain.Roulette;

import java.util.Random;

public class Roulette {
    private final String[] prizes;
    private final int[] probabilities;
    private final Random random;

    public Roulette(String[] prizes, int[] probabilities) {
        this.prizes = prizes;
        this.probabilities = probabilities;
        this.random = new Random();
    }

    public String spin() {
        int total = 0;
        for (int probability : probabilities) {
            total += probability;
        }

        int randomValue = random.nextInt(total);
        int cumulativeSum = 0;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeSum += probabilities[i];
            if (randomValue < cumulativeSum) {
                return prizes[i]; // 해당 상금을 반환
            }
        }
        return null; // 오류 발생 시
    }
}

