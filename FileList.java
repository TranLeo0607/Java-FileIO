//Name: Leo Tran

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class FileList<E extends Number> extends FileContainer implements List<E> {
	private File f1; //Declaring the file.
	private static int tempCounter = 0; //Counter for files
	private int size; //Keeps track of the size of the file.

	/**
	 * FileList constructor that creates a blank FileList.
	 * Blank Constructor that calls the second constructor and passes a name.
	 */
	public FileList() { 
		this("FileList" + tempCounter + Long.toString(System.currentTimeMillis()) + ".txt");
	}

	/**
	 * FileList constructor that creates a FileList with what is passed through the parameter.
	 * @param fileName
	 */
	public FileList(String fileName) {
		File f2 = new File(fileName);
		try {
			if (f2.exists()) {
				f1 = f2;
				size = this.sizeSetter();
			} else {
				Thread.sleep(1);
				f2.createNewFile();
				f1 = f2;
				size = 0;
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Exception someUnneededMethod() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 *  A method of copying a file using InputStream and OutputStream
	 * Learned from journal dev copy file.
	 * https://www.journaldev.com/861/java-copy-file
	 * Created by Pankaj
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	private static void copyFileUsingStream(File original, File copy) throws IOException {
	    InputStream inputStream = null;
	    OutputStream outputStream = null;
	    try {
	        inputStream = new FileInputStream(original);
	        outputStream = new FileOutputStream(copy);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = inputStream.read(buffer)) > 0) { //
	            outputStream.write(buffer, 0, length);
	        }
	    } finally {//Makes sure to close the input and output streams once it reaches the end.
	        inputStream.close();
	        outputStream.close();
	    }
	}
	/**
	 * Used to determine and attach the type into the same line of the file.
	 * @param e
	 * @return findType
	 */
	private StringBuilder strBuilder(E e) {
		StringBuilder findType = new StringBuilder(e.getClass().getName());
		findType.delete(0, 10);
		return findType;
	}
	/**
	 * Function that adds an element towards the end of the file.
	 * 
	 * @param e.
	 * @throws NullPointerException.
	 * @throws ClassCastException.
	 * @return true if the value was added, false if it wasn't.
	 */
	public boolean add(E e) {
		if(e == null) {
			throw new NullPointerException("");
		}
		if(!((e.getClass().getSimpleName()).matches("Double|Float|Integer|Byte|Long|Short"))) {
			throw new ClassCastException("");
		}
		StringBuilder findType = strBuilder(e);
		boolean checker = false;
		try {
			FileWriter fr = new FileWriter(f1, true);
			BufferedWriter wr = new BufferedWriter(fr);
			wr.write(findType.charAt(0) + e.toString() + "\n");
			wr.flush();
			wr.close();
			checker = true;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		size++;
		return checker;
	}
	/**
	 *Function adds an element to whatever line indicated by the index.
	 *@param index.
	 *@param element.
	 *@throws NullPointerExeception.
	 *@throws IndexOutOfBoundsException.
	 */
	public void add(int index, E element) {
		if(element == null) {
			throw new NullPointerException("");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(String.format("Index: %d Size: %d",index,size));
		}
		
		try {
			File temp = new File("temp.txt");
			StringBuilder findType = strBuilder(element);
			FileWriter f2 = new FileWriter(temp, false);
			BufferedWriter wr = new BufferedWriter(f2);
			String tempString = null;
			int i = 0;
			int s = size;
			BufferedReader re = new BufferedReader(new FileReader(f1));
			while (i <= s) {
				if (i == index) {
					wr.write(findType.charAt(0) + element.toString() + "\n");
					size++;
				} else {
					if ((tempString =re.readLine())!= null) {
						wr.write(tempString + "\n");
					}
				}
				wr.flush();
				i++;
			}
			re.close();
			wr.close();
			copyFileUsingStream(temp, f1);
			temp.deleteOnExit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * (non-Javadoc)
	 * @see java.util.List#clear()
	 */
	public void clear() {
		try {
			FileWriter fr = new FileWriter(f1, false);
			fr.close();
			this.size = 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Function removes a value at a certain index then stores the value and returns it.
	 * @param index.
	 * @throws IndexOutOfBoundsException.
	 * @return value that was removed.
	 */
	@SuppressWarnings("unchecked")
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(String.format("Index: %d Size: %d",index,size));
		}
		File temp = new File("temp2.txt");
		Object storageValue = null;
		try {
			FileWriter f2 = new FileWriter(temp, false);
			BufferedWriter wr = new BufferedWriter(f2);
			String temp2 = null;
			BufferedReader re = new BufferedReader(new FileReader(f1));
			for (int i = 0; ((temp2 = re.readLine()) != null); i++) {
				if (i != index) {
					wr.write(temp2 + "\n");
				} else {
					storageValue = this.get(temp2);
					size--;
				}
			}
			wr.flush();
			wr.close();
			re.close();
			copyFileUsingStream(temp, f1);
			temp.deleteOnExit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (E) storageValue;
	}
	/**
	 * Helper method that was created to determine what type the value is, then converts the object to that value.
	 * @param nextLine.
	 * @return the converted value.
	 */
	private Object get(String nextLine) {
		// TODO Auto-generated method stub
		if (nextLine.contains("B")) {
			return Byte.parseByte(nextLine.substring(1));
		} else if (nextLine.contains("L")) {
			return Long.parseLong(nextLine.substring(1));
		} else if (nextLine.contains("I")) {
			return Integer.parseInt(nextLine.substring(1));
		} else if (nextLine.contains("D")) {
			return Double.parseDouble(nextLine.substring(1));
		} else if (nextLine.contains("F")) {
			return Float.parseFloat(nextLine.substring(1));
		} else {
			return Short.parseShort(nextLine.substring(1));
		}
	}

	/**
	 * Function checks for the object and then removes it once found.
	 * @param o.
	 * @throws NullPointerException.
	 * @throws ClassCastException.
	 * @return true or false depending on if the object was deleted.
	 */
	public boolean remove(Object o) {
		if(o == null) {
			throw new NullPointerException("");
		}
		if(!((o.getClass().getSimpleName()).matches("Double|Float|Integer|Byte|Long|Short"))) {
			throw new ClassCastException("");
		}
		File temp = new File("temp2.txt");
		boolean deleted = false;
		// Object savedValue = null;
		try {
			
			String temp2 = null;
			FileWriter f2 = new FileWriter(temp, false);
			BufferedWriter wr = new BufferedWriter(f2);
			BufferedReader re2 = new BufferedReader(new FileReader(f1));
			for (int i = 0; ((temp2 = re2.readLine()) != null); i++) {
				if (this.get(i).equals(o) && deleted == false) {
					deleted = true;
					size--;
				} else {
					wr.write(temp2 + "\n");
				}
			}
			re2.close();
			wr.flush();
			wr.close();
			copyFileUsingStream(temp, f1);
			temp.deleteOnExit();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		return deleted;
	}

	/**
	 * Function that makes the value to a  string.
	 * @return A string of characters that was in the list.
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "";
		try {
			BufferedReader re = new BufferedReader(new FileReader(f1));
			s += "[";
			String temp2 = null;
			while ((temp2 = re.readLine()) != null) {
				s += temp2.substring(1) + ", ";
			}
			if (s != null && s.length() > 1) {
				s = s.substring(0, s.length() - 2);
			}
			s += "]";
			re.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
	}
	/**
	 * Helper Method that sets the size of the file.
	 * @return the size of the file.
	 */
	private int sizeSetter() {
		int tempSize = 0;
		try {
			BufferedReader re = new BufferedReader(new FileReader(f1));
			while (re.readLine() != null) {
				tempSize++;
			}
			re.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tempSize;
	}
	/**
	 * Returns the number of elements in the list.
	 */	
	public int size() {
		return size;
	}
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return false;
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int arg0) {
		// TODO Auto-generated method stub
		Object temp = null;
		String temp2 = null;
		try {
			BufferedReader re = new BufferedReader(new FileReader(f1));
			for (int i = 0; (temp2 = re.readLine()) != null; i++) {
				if (i == arg0) {
					temp = this.get(temp2);
				}
			}
			re.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (E) temp;
	}

	@Override
	public int indexOf(Object arg0) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return null;
	}

	@Override
	public int lastIndexOf(Object arg0) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return false;
	}

	@Override
	public E set(int arg0, E arg1) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return null;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		someUnneededMethod();
		return null;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return f1.getName();
	}

	@Override
	public long getFileSize() {
		// TODO Auto-generated method stub
		return f1.length();
	}
}
