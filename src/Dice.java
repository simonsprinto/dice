public class Dice {
    public boolean checkNumberOfDices(int number) {
        if(number >= 1 && number <= 5) {
            return true;
        } else {
            return false;
        }
    }

    public int throwDice() {
            int max = 6;
            int min = 1;
            int range = max - min + 1;

            int result = (int) (Math.random() * range ) + min;

            return result;
    }
}
