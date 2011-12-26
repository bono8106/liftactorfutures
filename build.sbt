name := "liftactorfutures"

addCompilerPlugin("org.scala-tools.sxr" %% "sxr" % "0.2.8-SNAPSHOT")

scalacOptions <+= scalaSource in Compile map { "-P:sxr:base-directory:" + _.getAbsolutePath }

resolvers += "Scala Tools Releases" at "http://scala-tools.org/repo-releases/"

libraryDependencies ++= Seq(
  "net.liftweb" %% "lift-actor" % "2.4-RC1" withSources
)

