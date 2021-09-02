package Steganograhpie.test;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Steganograhpie.code.PictureHistogram;
import Steganograhpie.code.SplitImage;
import Steganograhpie.code.SplitImage3Color;
import de.informatics4kids.Picture;
import de.informatics4kids.PictureViewer;

public class Test {

	public static void main(String[] args) {

		JFileChooser fc = new JFileChooser();
		JButton b = new JButton();

		fc.showOpenDialog(b);

		File f = fc.getSelectedFile();

		if (!(f.getAbsolutePath().endsWith(".png") || f.getAbsolutePath().endsWith(".jpg"))) {
			JOptionPane.showMessageDialog(null, "The Selected File is not a PNG or JPG", "Wrong file type Exeption",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		Picture p = new Picture(f.getAbsolutePath());

		Picture[] rgbP = SplitImage3Color.splitImageRBG(p);

		PictureViewer v = new PictureViewer(rgbP[0].getPicture());
		v.show();

		PictureViewer v2 = new PictureViewer(rgbP[1].getPicture());
		v2.show();

		PictureViewer v3 = new PictureViewer(rgbP[2].getPicture());
		v3.show();

		String name = f.getName().substring(0, f.getName().length() - 4);

		PictureHistogram.getHistogram(p, f.getAbsolutePath().replace(f.getName(), name + "-histogram.dat"));

		Picture[] split = SplitImage.splitGrayImage(SplitImage.grayScale(p));

		split[0].save(f.getAbsolutePath().replace(name, name + "-part1"));

		split[1].save(f.getAbsolutePath().replace(name, name + "-part2"));

	}

}
