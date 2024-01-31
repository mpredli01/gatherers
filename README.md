## Stream Gatherers for Custom Intermediate Stream Operations

Example applications on how to use JEP 461, Stream Gatherers (Preview)



Since this is a preview feature, the --enable-preview compiler option must be used on the command line or defined in Maven

### Command Line

#### Compile

`/Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home/bin/javac src/main/java/org/redlich/gatherers/*.java --enable-preview --release 22 -d target/classes`

#### Execute

Change directory to `target/classes`

`/Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home/bin/java --enable-preview org/redlich/gatherers/Application`

`/Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home/bin/java --enable-preview org/redlich/gatherers/DistinctByLength`
                
### Maven

These properties and plugin are defined in the `pom.xml` file:

`<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>22</maven.compiler.source>
    <maven.compiler.target>22</maven.compiler.target>
    <exec.mojo.version>3.1.0</exec.mojo.version>
    <mainClass>org.redlich.gatherers.FixedWindow</mainClass>
</properties>`

`<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>${exec.mojo.version}</version>
    <configuration>
        <mainClass>${mainClass}</mainClass>
        <arguments>
            <arg>--enable-preview</arg>
        </arguments>
    </configuration>
</plugin>`
