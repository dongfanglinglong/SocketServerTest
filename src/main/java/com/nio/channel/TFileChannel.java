package com.nio.channel;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by dongfang on 16/4/9.
 */
public class TFileChannel {
    public static void main(String[] args) {
        System.out.println("TFileChannel");

        try {
            RandomAccessFile aFile = new RandomAccessFile("/Users/dongfang/Downloads/untitled.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

            //create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf); //read into buffer.
            while (bytesRead != -1) {
                buf.flip();  //make buffer ready for read
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get()); // read 1 byte at a time
                }

                buf.clear(); //make buffer ready for writing
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // transferFrom();
        transferTo();
    }


    private static void transferFrom() {
        RandomAccessFile fromFile = null;
        try {
            fromFile = new RandomAccessFile("/Users/dongfang/Downloads/untitled.txt", "rw");

            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("/Users/dongfang/Downloads/untitled1.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();

            toChannel.transferFrom(fromChannel, position, count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void transferTo() {
        RandomAccessFile fromFile = null;
        try {
            fromFile = new RandomAccessFile("/Users/dongfang/Downloads/untitled.txt", "rw");

            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("/Users/dongfang/Downloads/untitled1.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();

            fromChannel.transferTo(position, count, toChannel);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private static void selector() {
//        try {
//            Selector selector = Selector.open();
//            channel.configureBlocking(false);
//            SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
//            while (true) {
//                int readyChannels = selector.select();
//                if (readyChannels == 0) continue;
//                Set selectedKeys = selector.selectedKeys();
//                Iterator keyIterator = selectedKeys.iterator();
//                while (keyIterator.hasNext()) {
//                    SelectionKey key = keyIterator.next();
//                    if (key.isAcceptable()) {
//                        // a connection was accepted by a ServerSocketChannel.
//                    } else if (key.isConnectable()) {
//                        // a connection was established with a remote server.
//                    } else if (key.isReadable()) {
//                        // a channel is ready for reading
//                    } else if (key.isWritable()) {
//                        // a channel is ready for writing
//                    }
//                    keyIterator.remove();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}