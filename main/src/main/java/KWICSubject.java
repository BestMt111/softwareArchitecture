public class KWICSubject extends Subject{
    public void startKWIC(){
        for (int i = 0; i < super.getVector().size(); ++i) {
            super.notifyOneObserver(i);
        }
    }
}
