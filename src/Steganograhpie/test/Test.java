package Steganograhpie.test;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Steganograhpie.code.SplitImage;
import de.informatics4kids.Picture;

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

		Picture[] split = SplitImage.splitGrayImage(SplitImage.grayScale(p));

		// PictureViewer v = new PictureViewer(split[0].getPicture());
		// v.show();

		// PictureViewer v2 = new PictureViewer(split[1].getPicture());
		// v2.show();

		String name = f.getName().substring(0, f.getName().length() - 4);
		String ending = f.getName().substring(f.getName().length() - 4, f.getName().length());
	

		split[0].save(f.getAbsolutePath().replace(name, name + "-part1"));

		split[1].save(f.getAbsolutePath().replace(name, name + "-part2"));
	}

}
