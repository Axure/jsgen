**Files:**

JavaScriptFromJson.java:

**Environment used:**

jdk:

java version "1.7.0\_21"

**classpath:**

gson-2.2.4.jar

**Usage:**

java JavaScriptFromJson \<json-file\>

**Output:**

javascript stubs printed on stdout

**Description:**

This program converts the \<json-file\> to javascript stubs suitable for
use in IDEs for auto-completion.

**Note:**

The program assumes that the \<json-file\> is one of the json files
available at
http://src.chromium.org/chrome/trunk/src/chrome/common/extensions/api/

**Example:**

javac JavaScriptFromJson.java \<json-file\>

(produces JavaScriptFromJson.class)

download
http://src.chromium.org/chrome/trunk/src/chrome/common/extensions/api/runtime.json
as runtime.json

java JavaScriptFromJson runtime.json \> runtime.js

load runtime.js as a library in WebStorm (or other IDE of choice).

