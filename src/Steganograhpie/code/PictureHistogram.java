package Steganograhpie.code;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import de.informatics4kids.Picture;
import de.informatics4kids.PictureViewer;

public class PictureHistogram {

	public static void getHistogram(Picture p, String path) {

		File output = new File(path);
		int[][] data = { new int[256], new int[256], new int[256] };

		for (int i = 0; i < p.widthX(); i++) {
			for (int j = 0; j < p.heightY(); j++) {
				data[0][p.getColor(i, j).getRed()]++;
				data[1][p.getColor(i, j).getGreen()]++;
				data[2][p.getColor(i, j).getBlue()]++;
			}

		}

		int maxred = 0;
		int maxgreen = 0;
		int maxblue = 0;

		for (int i = 0; i < data[0].length; i++) {
			maxred = maxred < data[0][i] ? data[0][i] : maxred;
			maxgreen = maxgreen < data[1][i] ? data[1][i] : maxgreen;
			maxblue = maxblue < data[2][i] ? data[2][i] : maxblue;
		}

		Picture red = new Picture(256, maxred);

		Picture green = new Picture(256, maxgreen);

		Picture blue = new Picture(256, maxblue);

		for (int i = 0; i < data[0].length; i++) {

			for (int j = 0; j < data[0][i]; j++) {
				red.setColor(i, j, Color.RED);
			}

			for (int j = 0; j < data[1][i]; j++) {
				green.setColor(i, j, Color.GREEN);
			}

			for (int j = 0; j < data[2][i]; j++) {
				blue.setColor(i, j, Color.BLUE);
			}

		}
		
		PictureViewer viewerRed = new PictureViewer(red.getPicture());

		PictureViewer viewerGreen= new PictureViewer(green.getPicture());

		PictureViewer viewerBlue = new PictureViewer(blue.getPicture());
		
		viewerRed.show();
		viewerGreen.show();
		viewerBlue.show();

	}

}
