package com.example.PaginaWebRufyan.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

	public static boolean deleteImageFiles(String path) {
		File file = new File(path);
		if (file.exists()) return file.delete();
		else {
			System.out.println("El archivo no existe");
			return false;
		}
	}
	public static byte[] compressImage(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4 * 1024];

		while (!deflater.finished()) {
			int size = deflater.deflate(tmp);
			outputStream.write(tmp, 0, size);
		}

		try {
			outputStream.close();
		} catch (Exception ignored) {
		}

		return outputStream.toByteArray();
	}

	public static byte[] decompressImage(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] tmp = new byte[4 * 1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(tmp);
				outputStream.write(tmp, 0, count);
			}
		} catch (Exception ignored) {

		}

		return outputStream.toByteArray();
	}

}
