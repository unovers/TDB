/**
 *
 */
package ch.heigvd.bachelor.crescenzio.generator.ults;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author Fabio
 */
public class Utils {
  public static File createTempDirectory()
      throws IOException
  {
    final File temp;

    temp = File.createTempFile("temp", Long.toString(System.nanoTime()));

    if (!(temp.delete()))
    {
      throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
    }

    if (!(temp.mkdir()))
    {
      throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
    }

    return (temp);
  }

  public static Element getDirectChild(Element parent, String name)
  {
    for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling())
    {
      if (child instanceof Element && name.equals(child.getNodeName())) return (Element) child;
    }
    return null;
  }

  /**
   * Size of the buffer to read/write data
   * FROM http://www.codejava.net/java-se/file-io/programmatically-extract-a-zip-file-using-java
   */

  private static final int BUFFER_SIZE = 4096;

  /**
   * Unzip it
   *
   * @param file
   *          input zip file
   * @param output
   *          zip file output folder
   * @throws IOException
   */
  public static void unZipIt(String file, String outputFolder) throws IOException {
    File destDir = new File(outputFolder);
    if (!destDir.exists()) {
      destDir.mkdir();
    }
    ZipInputStream zipIn = new ZipInputStream(new FileInputStream(file));
    ZipEntry entry = zipIn.getNextEntry();
    // iterates over entries in the zip file
    while (entry != null) {
      String filePath = outputFolder + File.separator + entry.getName();
      if (!entry.isDirectory()) {
        // if the entry is a file, extracts it
        extractFile(zipIn, filePath);
      }
      else {
        // if the entry is a directory, make the directory
        File dir = new File(filePath);
        dir.mkdir();
      }
      zipIn.closeEntry();
      entry = zipIn.getNextEntry();
    }
    zipIn.close();
  }

  private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
    byte[] bytesIn = new byte[BUFFER_SIZE];
    int read = 0;
    while ((read = zipIn.read(bytesIn)) != -1) {
      bos.write(bytesIn, 0, read);
    }
    bos.close();
  }
}
