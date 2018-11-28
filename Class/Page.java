
public abstract class Page {
	private String pageName;
	private String menuString;
	public static void clearConsole() {
		System.out.print("\033[H\033[2J");
	}
	public abstract void backtoPrevious();
}
