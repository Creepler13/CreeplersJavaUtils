package Steganograhpie.code;

import java.awt.Color;

import de.informatics4kids.Picture;

public class SplitImage3Color {

	public static Picture[] splitImageRBG(Picture p) {
		Picture[] pics = { new Picture(p.widthX(), p.heightY()), new Picture(p.widthX(), p.heightY()),
				new Picture(p.widthX(), p.heightY()) };

		for (int i = 0; i < p.widthX(); i++) {
			for (int j = 0; j < p.heightY(); j++) {
				Color c = p.getColor(i, j);
				pics[0].setColor(i, j, new Color(c.getRed(), 0, 0));
				pics[1].setColor(i, j, new Color(0, c.getGreen(), 0));
				pics[2].setColor(i, j, new Color(0, 0, c.getBlue()));
			}
		}

		return pics;
	}

}
