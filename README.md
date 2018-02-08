## Snappy-tool
*Simple compression/decompression tool using Snappy written in Scala*

#### Requirements
SBT https://www.scala-sbt.org/ as build tool

#### Build
To create an tgz archive containing an executable:  
`sbt universal:packageZipTarball`  
The archive will be located under `target/universal/<archive>`

for more options refer to: https://www.scala-sbt.org/sbt-native-packager/

#### Run
`snappy (c|x) <in-file> <out-file> [<buffer-size>]`


