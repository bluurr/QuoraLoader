# Quora loader

A read-only locator and extraction library for Quora questions and answers.

## Getting Started
This library allows you to login, search and retrieve answers for a question. As no public API is provided by the Quora platform at this current time. The library uses the Selenium framework to retrieve the data from the Quora platform.

### Prerequisite
The library requires the following minimum versions
1. Java 8
2. Maven 3 or higher

#### Selenium
You must have a Selenium supported browser and driver installed on the desired machine which can be found [here](http://www.seleniumhq.org/download).

###### Chrome
The integration tests are configured currently to use the Chrome Selenium driver which can be downloaded [here](https://sites.google.com/a/chromium.org/chromedriver/).

The following property is required with the location of the Chrome driver `-Dwebdriver.chrome.driver={install_location/chromedriver.exe}`

The headless mode can be controlled via `-Dwebdriver.headless={true|false}`

### Usage
Below is an example of how to login, search and load back a question.
```Java
/** Set-up Chrome driver **/
BotExtra.setDriver(new ChromeDriver());

final String QUORA_LOCATION = "https://www.quora.com";

/** Login to the Quora platform **/
DashBoardPage dashboard = LoginPage.open(URI.create(QUORA_LOCATION)).login("{username}", "{password}");

/** Search the term Java on the Quora platform **/
SearchPage searchPage = dashboard.search("Java");

/** Load a summary of up to 10 questions. **/
List<QuestionSummary> questions = searchPage.getQuestions(10);

/** Load a Question and up to 5 answers **/
String location = questions.get(0).getLocation();
Question fullQuestion = QuestionPage.open(location).getQuestion(Answers.limit(5));

/** Clean up **/
BotExtra.closeDriver();
```
### Tests
To run the integration tests the following properties need to be set:
1. `-Dwebdriver.chrome.driver={install_location/chromedriver.exe}`
2. `-Dquora.login.username={username}`
3. `-Dquora.login.password={password}`

## Compliance

As per the Quora terms of service [TOS](https://www.quora.com/about/tos) for machine driven tools - 

1. You must use a descriptive user agent header.
2. You must follow robots.txt at all times.
3. Your access must not adversely affect any aspect of the Quora Platform�s functioning.
4. You must make it clear how to contact you, either in your user agent string, or on your website if you have one.

### Note
The integration tests require the following property to be define `-Dquora.contact={contact_here}` to meet these requirements.

## License
Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.