# FoxLib
Repository of FoxLib library for easier and better Spigot development.

## Importing as dependency in Maven
```xml
<repository>
  <id>foxlib-repo</id>
  <url>https://raw.githubusercontent.com/Foxyg3n/FoxLib/release/</url>
    <snapshots>
        <enabled>true</enabled>
        <updatePolicy>always</updatePolicy>
    </snapshots>
</repository>
```
```xml
<dependency>
  <groupId>me.foxyg3n</groupId>
  <artifactId>FoxLib</artifactId>
  <version>1.1-SNAPSHOT</version>
</dependency>
```
Make sure to shade the library into your build or if you're using Intellij,
extract the library it into your build in Project Structure

## Shade plugin
```xml
...
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-shade-plugin</artifactId>
      <version>3.4.1</version>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>shade</goal>
          </goals>
          <configuration>
            <filters>
              <filter>
                <artifact>me.foxyg3n:FoxLib</artifact>
                <includes>
                  <include>**</include>
                </includes>
              </filter>
            </filters>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
...
```
