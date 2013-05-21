/*
 * Author: Amitabh Ojha, QuickrWorld.com
 * Date Created: 2013-05-20
 */
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class JavaScriptFromJson {
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}

	static void process(String json) {
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
		JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
		String namespace = null;
		if (jsonObject.get("namespace") != null) {
			namespace = jsonObject.get("namespace").getAsString();
			codeNamespaceString(namespace);
		}
		if (jsonObject.get("properties") != null) {
			codePropertiesObject(jsonObject.get("properties").getAsJsonObject());
		}
		if (jsonObject.get("types") != null) {
			codeTypesArray(jsonObject.get("types").getAsJsonArray());
		}
		if (jsonObject.get("functions") != null) {
			codeFunctionsArray(jsonObject.get("functions").getAsJsonArray(),
					namespace);
		}
		if (jsonObject.get("events") != null) {
			// the "events" key also stores an array of functions as its value
			codeFunctionsArray(jsonObject.get("events").getAsJsonArray(),
					namespace);
		}
	}

	static void codeNamespaceString(String namespace) {
		if (namespace != null) {
			System.out.println(String.format("var chrome = chrome || {};"));
			System.out.println(String.format("chrome.%s = chrome.%s || {};",
					namespace, namespace));
		} else {
			System.out.println("/* warning: no namespace */");
		}
	}

	static void codePropertiesObject(JsonObject propertiesObject) {
		// TODO
	}

	static void codeTypesArray(JsonArray typesArray) {
		// TODO
	}

	static void codeFunctionsArray(JsonArray functionsArray, String namespace) {
		for (int i = 0; i < functionsArray.size(); i++) {
			JsonObject jsonObject = functionsArray.get(i).getAsJsonObject();
			String name = null;
			if (jsonObject.get("name") != null) {
				name = jsonObject.get("name").getAsString();
			}
			String types = null;
			if (jsonObject.get("types") != null) {
				types = jsonObject.get("types").getAsString();
			}
			String description = null;
			if (jsonObject.get("description") != null) {
				jsonObject.get("description").getAsString();
			}
			JsonArray parametersArray = null;
			if (jsonObject.get("parameters") != null) {
				parametersArray = jsonObject.get("parameters").getAsJsonArray();
			}
			JsonObject returnsObject = null;
			if (jsonObject.get("returns") != null) {
				returnsObject = jsonObject.get("returns").getAsJsonObject();
			}
			codeCommentStart();
			if (parametersArray != null) {
				codeParametersArray(parametersArray);
			}
			if (returnsObject != null) {
				codeReturnsObject(returnsObject);
			}
			codeCommentEnd();
			codeSignature(namespace, name, parametersArray);
		}
	}

	static void codeCommentStart() {
		System.out.println("/**");
		System.out.println(" *");
	}

	static void codeCommentEnd() {
		System.out.println(" *");
		System.out.println(" */");
	}

	static void codeParametersArray(JsonArray parametersArray) {
		for (int i = 0; i < parametersArray.size(); i++) {
			JsonObject jsonObject = null;
			if (parametersArray.get(i) != null) {
				jsonObject = parametersArray.get(i).getAsJsonObject();
				codeParameter(jsonObject);
			}
		}
	}

	static void codeParameter(JsonObject parameter) {
		String name = null;
		if (parameter.get("name") != null) {
			name = parameter.get("name").getAsString();
		}
		String ref = null;
		if (parameter.get("$ref") != null) {
			ref = parameter.get("$ref").getAsString();
		}
		String type = null;
		if (parameter.get("type") != null) {
			type = parameter.get("type").getAsString();
		}
		String typeString = null;
		if (ref != null) {
			typeString = "{" + ref + "}";
		}
		if (type != null) {
			typeString = "{" + type + "}";
		}
		JsonArray choices = null;
		if (parameter.get("choices") != null) {
			choices = parameter.get("choices").getAsJsonArray();
			String choicesString = null;
			for (int i = 0; i < choices.size(); i++) {
				if (choices.get(i) != null) {
					JsonObject choice = choices.get(i).getAsJsonObject();
					if (choice.get("type") != null) {
						String choiceType = choice.get("type").getAsString();
						if (choicesString == null) {
							choicesString = choiceType;
						} else {
							choicesString = choicesString + "|" + choiceType;
						}
					}
				}
			}
			if (choicesString != null) {
				typeString = "{" + choicesString + "}";
			}
		}
		String description = null;
		if (parameter.get("description") != null) {
			description = parameter.get("description").getAsString();
		}
		String optionalOpen = "";
		String optionalClose = "";
		boolean optional;
		if (parameter.get("optional") != null) {
			optional = parameter.get("optional").getAsBoolean();
			if (optional == true) {
				optionalOpen = "[";
				optionalClose = "]";
			}
		}
		if (name != null) {
			System.out.println(String.format(" * @param %s %s%s%s %s",
					typeString, optionalOpen, name, optionalClose,
					description == null ? "" : description));
		}
	}

	static void codeReturnsObject(JsonObject returnsObject) {
		String type = null;
		if (returnsObject.get("type") != null) {
			type = returnsObject.get("type").getAsString();
		}
		String ref = null;
		if (returnsObject.get("$ref") != null) {
			ref = returnsObject.get("$ref").getAsString();
		}
		String description = null;
		if (returnsObject.get("description") != null) {
			description = returnsObject.get("description").getAsString();
		}
		boolean optional = false;
		if (returnsObject.get("optional") != null) {
			optional = returnsObject.get("optional").getAsBoolean();
		}
		String returnTypeString = null;
		if((type == null) && (ref == null)) { returnTypeString = "any"; }
		if(ref != null) { returnTypeString = ref; }
		if(type != null) { returnTypeString = type; } // clobbers ref
		System.out.println(String.format(" * @returns {%s%s} %s",
				optional == true ? "optional " : "", returnTypeString,
				description == null ? "" : description));
	}

	static void codeSignature(String namespace, String name,
			JsonArray parametersArray) {
		codeSignatureStart(namespace, name);
		codeSignatureParameters(parametersArray);
		codeSignatureEnd();
	}

	static void codeSignatureStart(String namespace, String name) {
		System.out.print(String.format("chrome.%s.%s = function(", namespace,
				name));
	}

	static void codeSignatureParameters(JsonArray parametersArray) {
		if (parametersArray != null) {
			for (int i = 0; i < parametersArray.size(); i++) {
				JsonObject jsonObject = null;
				if (parametersArray.get(i) != null) {
					jsonObject = parametersArray.get(i).getAsJsonObject();
					codeSignatureParameter(jsonObject,
							i == parametersArray.size() - 1);
				}
			}
		}
	}

	static void codeSignatureParameter(JsonObject parameter,
			boolean isLastParameter) {
		String name = null;
		if (parameter.get("name") != null) {
			name = parameter.get("name").getAsString();
		}
		if (name != null) {
			System.out.print(String.format("%s%s", name, isLastParameter ? ""
					: ", "));
		}
	}

	static void codeSignatureEnd() {
		System.out.println(") {};"); // Yes, the empty code block, too
	}

	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("usage:");
			System.err.println("  java JavaScriptFromJson <json-file>");			
		}
		try {
			String jsonFileContent = readFile(args[0], Charset.defaultCharset());
			process(jsonFileContent);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
