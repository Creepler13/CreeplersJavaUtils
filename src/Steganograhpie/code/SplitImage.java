package Steganograhpie.code;

import java.awt.Color;
import java.util.Random;

import de.informatics4kids.Picture;

public class SplitImage {

	public static Picture grayScale(Picture p) {
		for (int i = 0; i < p.widthX(); i++) {
			for (int j = 0; j < p.heightY(); j++) {
				Color c = p.getColor(i, j);
				Color fill = Color.WHITE;
				if (c.getGreen() + c.getBlue() + c.getRed() > 382.5) {
					fill = Color.BLACK;
				}

				p.setColor(i, j, fill);

			}
		}
		return p;
	}

	public static Picture[] splitGrayImage(Picture p) {

		Picture[] ret = { new Picture(p.widthX(), p.heightY()), new Picture(p.widthX(), p.heightY()) };

		Random rand = new Random();

		for (int i = 0; i < p.widthX(); i++) {
			for (int j = 0; j < p.heightY(); j++) {

				int t = 0;
				int o = p.getColor(i, j).equals(Color.WHITE) ? 0 : 1;

				if (rand.nextBoolean()) {
					ret[0].setColor(i, j, Color.WHITE);

				} else {
					ret[0].setColor(i, j, Color.BLACK);
					t = 1;
				}

				ret[1].setColor(i, j, ((t + o) % 2) == 0 ? Color.WHITE : Color.BLACK);

			}
		}

		return ret;

	}

}
