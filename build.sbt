import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences.{AlignArguments, AlignParameters, AlignSingleLineCaseStatements, CompactControlReadability, DanglingCloseParenthesis, DoubleIndentClassDeclaration, FormatXml, FormattingPreferences, IndentSpaces, IndentWithTabs, MultilineScaladocCommentsStartOnFirstLine, PlaceScaladocAsterisksBeneathSecondAsterisk, Preserve, PreserveSpaceBeforeArguments, SpaceBeforeColon, SpaceInsideBrackets, SpaceInsideParentheses, SpacesAroundMultiImports, SpacesWithinPatternBinders}


name := "skeleton"

organization := "com.cascadeofinsights"

version := "1.2"

scalaVersion := "2.11.7"

resolvers += Resolver.sonatypeRepo("snapshots")

addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1-SNAPSHOT")

//Define dependencies. These ones are only required for Test and Integration Test scopes.
libraryDependencies ++= Seq(
    "org.scalatest"   %% "scalatest"    % "2.2.4"   % "test,it"
)

lazy val formatSettings = SbtScalariform.scalariformSettings ++ Seq(
  ScalariformKeys.preferences := FormattingPreferences()
    .setPreference(AlignArguments, false)
    .setPreference(AlignParameters, false)
    .setPreference(AlignSingleLineCaseStatements, false)
    .setPreference(CompactControlReadability, false)
    .setPreference(DoubleIndentClassDeclaration, true)
    .setPreference(DanglingCloseParenthesis, Preserve)
    .setPreference(FormatXml, true)
    .setPreference(IndentSpaces, 2)
    .setPreference(IndentWithTabs, false)
    .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)
    .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)
    .setPreference(PreserveSpaceBeforeArguments, true)
    .setPreference(SpacesAroundMultiImports, true)
    .setPreference(SpaceBeforeColon, false)
    .setPreference(SpaceInsideBrackets, false)
    .setPreference(SpaceInsideParentheses, false)
    .setPreference(SpacesWithinPatternBinders, true)
)

scalacOptions ++= List("-feature","-deprecation", "-unchecked", "-Xlint")

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-l", "org.scalatest.tags.Slow", "-u","target/junit-xml-reports", "-oD", "-eS")

//Style Check section
scalastyleConfig <<= baseDirectory { _ / "src/main/config" / "scalastyle-config.xml" }

coverageEnabled := true