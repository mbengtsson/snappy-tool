package se.marcusbengtsson

import java.io.{FileInputStream, FileOutputStream, InputStream, OutputStream}

import org.xerial.snappy.{SnappyFramedInputStream, SnappyFramedOutputStream}

case class Args(option: String, output: String, input: List[String])

object snappy extends App {

  args.toList match {
    case x :: xs if x == "c" => doSnappy(compress)(xs)
    case x :: xs if x == "x" => doSnappy(decompress)(xs)
    case _ => printUsage()
  }

  def doSnappy(f: (String, String, Int) => Unit)(args: List[String]): Unit = args match {
    case in :: out :: bs :: ys if bs forall Character.isDigit => (in, out, bs.toInt)
    case in :: out :: ys => f(in, out, 1024)
    case _ => printUsage()
  }

  def printUsage(): Unit = println("Usage: snappy [c|x] in-file out-file <buffer_size>")

  def compress(in: String, out: String, bufferSize: Int): Unit = streamCopy(
    new FileInputStream(in),
    new SnappyFramedOutputStream(new FileOutputStream(out)),
    bufferSize
  )

  def decompress(in: String, out: String, bufferSize: Int): Unit = streamCopy(
    new SnappyFramedInputStream(new FileInputStream(in)),
    new FileOutputStream(out),
    bufferSize
  )

  def streamCopy(in: InputStream, out: OutputStream, bufferSize: Int): Unit = try {
    val buffer = new Array[Byte](bufferSize)
    Iterator.continually(in read buffer).takeWhile(-1 !=).foreach(read => out.write(buffer, 0, read))
  } finally {
    if (out != null) out.close()
    if (in != null) in.close()
  }
}


