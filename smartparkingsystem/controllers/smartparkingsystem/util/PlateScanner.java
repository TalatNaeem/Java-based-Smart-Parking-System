package smartparkingsystem.util;
/*package smartparkingsystem.util;

import java.io.File;

public class PlateScanner {

	public String getLicenseNumber(File imageFile) {
		ITesseract iTesseract = new Tesseract();
		String result = null;

		try {
			result = iTesseract.doOCR(imageFile);
		} catch (TesseractException e) {
			System.out.println("Caught a TesseractException when reading a plate!");
		}

		return result.substring(0, result.length() - 2);
	}
}
*/