package cyri.renderer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.el.StaticFieldELResolver;

import cyri.model.DBManga;

public class CSSCharger {

	private String myCSS;

	private static CSSCharger INSTANCE;

	public CSSCharger() {
		initCharger();
	}

	private void initCharger() {
		File ressource = new File(this.getClass()
				.getResource("/../stylesheets/styles.css").getFile());

		if (ressource.exists()) {
			System.out.println("exists");
			StringBuilder result = new StringBuilder("");
			Scanner scanner = null;
			try {
				scanner = new Scanner(ressource);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					result.append(line).append("\n");
				}

				scanner.close();

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				scanner.close();
			}
			myCSS = result.toString();
		}
	}

	public String getMyCSS() {
		return myCSS;
	}

	public static CSSCharger getInstance() {
		if (INSTANCE == null)
			INSTANCE = new CSSCharger();

		return INSTANCE;
	}

}
