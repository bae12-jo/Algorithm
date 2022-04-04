import java.io.*;
import java.util.*;

public class Main{
	
	static FastIO io = new FastIO();
	
	public static void main(String[] args){

		int p = io.nextInt();
		int q = io.nextInt();
		
		int count = 0;
		int divisor = 0;
		for(int i=1; i<=p; ++i){
			if(p%i==0) {
				count++;
				if(count==q){
					io.print(i);
					io.flushbuffer();
					break;
				}
			}
		}
		
		if(count!=q){
			io.print(0);
			io.flushbuffer();
		}
		
	}
	
	private static class FastIO {
		private static final int EOF = -1;
		
		private static final byte ASCII_space = 32;
		private static final byte ASCII_minus = 45;
		private static final byte ASCII_0 = 48;
		private static final byte ASCII_9 = 57;
		
		private final DataInputStream din;
		private final DataOutputStream dout;
		
		private byte[] inbuffer;
		private int inbufferpointer, bytesread;
		private byte[] outbuffer;
		private int outbufferpointer;
		
		private byte[] bytebuffer;
		
		private FastIO() {
			this.din = new DataInputStream(System.in);
			this.dout = new DataOutputStream(System.out);
			
			this.inbuffer = new byte[65536];
			this.inbufferpointer = 0;
			this.bytesread = 0;
			this.outbuffer = new byte[65536];
			this.outbufferpointer = 0;
			
			this.bytebuffer = new byte[20];
		}
		
		private byte read() {
			if (inbufferpointer == bytesread)
				fillbuffer();
			return bytesread == EOF ? EOF : inbuffer[inbufferpointer++];
		}
		
		private void fillbuffer() {
			try {
				bytesread = din.read(inbuffer, inbufferpointer = 0, inbuffer.length);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		private void write(byte b) {
			if (outbufferpointer == outbuffer.length)
				flushbuffer();
			outbuffer[outbufferpointer++] = b;
		}
		
		private void flushbuffer() {
			if (outbufferpointer != 0) {
				try {
					dout.write(outbuffer, 0, outbufferpointer);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				outbufferpointer = 0;
			}
		}
		
		private int nextInt() {
			byte b;
			while(isSpace(b = read()))
				;
			boolean negative = false;
			if (b == '-') {
				negative = true;
				b = read();
			}
			int result = b - '0';
			while (isDigit(b = read()))
				result = result * 10 + b - '0';
			return negative ? -result : result;
		}
		
		private void print(int i) {
			if (i == 0) {
				write(ASCII_0);
			} else {
				if (i < 0) {
					write(ASCII_minus);
					i = -i;
				}
				int index = 0;
				while (i > 0) {
					bytebuffer[index++] = (byte) ((i % 10) + ASCII_0);
					i /= 10;
				}
				while (index-- > 0) {
					write(bytebuffer[index]);
				}
			}
		}
		
		private boolean isSpace(byte b) {
			return b <= ASCII_space && b >= 0;
		}
		
		private boolean isDigit(byte b) {
			return b >= ASCII_0 && b <= ASCII_9;
		}
	}

}
