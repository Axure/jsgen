var chrome = chrome || {};
chrome.runtime = chrome.runtime || {};
/**
 *
 * @param {function} callback 
 *
 */
chrome.runtime.getBackgroundPage = function(callback) {};
/**
 *
 * @returns {object} The manifest details.
 *
 */
chrome.runtime.getManifest = function() {};
/**
 *
 * @param {string} path A path to a resource within an app/extension expressed relative to its install directory.
 * @returns {string} The fully-qualified URL to the resource.
 *
 */
chrome.runtime.getURL = function(path) {};
/**
 *
 *
 */
chrome.runtime.reload = function() {};
/**
 *
 * @param {function} callback 
 *
 */
chrome.runtime.requestUpdateCheck = function(callback) {};
/**
 *
 * @param {string} [extensionId] The ID of the extension/app you want to connect to. If omitted, default is your own extension.
 * @param {object} [connectInfo] 
 * @returns {Port} Port through which messages can be sent and received. The port's $ref:[runtime.Port onDisconnect] event is fired if the extension/app does not exist. 
 *
 */
chrome.runtime.connect = function(extensionId, connectInfo) {};
/**
 *
 * @param {string} application The name of the registered application to connect to.
 * @returns {Port} Port through which messages can be sent and received with the application
 *
 */
chrome.runtime.connectNative = function(application) {};
/**
 *
 * @param {string} [extensionId] The extension ID of the extension you want to connect to. If omitted, default is your own extension.
 * @param {any} message 
 * @param {function} [responseCallback] 
 *
 */
chrome.runtime.sendMessage = function(extensionId, message, responseCallback) {};
/**
 *
 * @param {string} application The name of the native messaging host.
 * @param {object} message The message that will be passed to the native messaging host.
 * @param {function} [responseCallback] 
 *
 */
chrome.runtime.sendNativeMessage = function(application, message, responseCallback) {};
/**
 *
 * @param {function} callback Called with results
 *
 */
chrome.runtime.getPlatformInfo = function(callback) {};
/**
 *
 *
 */
chrome.runtime.onStartup = function() {};
/**
 *
 * @param {object} details 
 *
 */
chrome.runtime.onInstalled = function(details) {};
/**
 *
 *
 */
chrome.runtime.onSuspend = function() {};
/**
 *
 *
 */
chrome.runtime.onSuspendCanceled = function() {};
/**
 *
 * @param {object} details The manifest details of the available update.
 *
 */
chrome.runtime.onUpdateAvailable = function(details) {};
/**
 *
 *
 */
chrome.runtime.onBrowserUpdateAvailable = function() {};
/**
 *
 * @param {Port} port 
 *
 */
chrome.runtime.onConnect = function(port) {};
/**
 *
 * @param {Port} port 
 *
 */
chrome.runtime.onConnectExternal = function(port) {};
/**
 *
 * @param {any} message The message sent by the calling script.
 * @param {MessageSender} sender 
 * @param {function} sendResponse Function to call (at most once) when you have a response. The argument should be any JSON-ifiable object. If you have more than one <code>onMessage</code> listener in the same document, then only one may send a response. This function becomes invalid when the event listener returns, unless you return true from the event listener to indicate you wish to send a response asynchronously (this will keep the message channel open to the other end until <code>sendResponse</code> is called).
 * @returns {optional boolean} Return true from the event listener if you wish to call <code>sendResponse</code> after the event listener returns.
 *
 */
chrome.runtime.onMessage = function(message, sender, sendResponse) {};
/**
 *
 * @param {any} message The message sent by the calling script.
 * @param {MessageSender} sender 
 * @param {function} sendResponse Function to call (at most once) when you have a response. The argument should be any JSON-ifiable object. If you have more than one <code>onMessage</code> listener in the same document, then only one may send a response. This function becomes invalid when the event listener returns, unless you return true from the event listener to indicate you wish to send a response asynchronously (this will keep the message channel open to the other end until <code>sendResponse</code> is called).
 * @returns {optional boolean} Return true from the event listener if you wish to call <code>sendResponse</code> after the event listener returns.
 *
 */
chrome.runtime.onMessageExternal = function(message, sender, sendResponse) {};
