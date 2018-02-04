# adidas-ui-tests
This is a project that has three UI tests scripted using Selenium+Java for Adidas assessment for Senior QA engineer

## Instructions
1. Download the code on your machine
2. Install `gradle` on your machine
3. In the terminal, navigate to the folder that contains `build.gradle` in this project
4. Run the command `gradle test`

## Reports
Reports can be viewed in the path `build/reports/tests/test/index.html`

## Some things to note
> Each test runs first on Chrome and then on Firefox

> The framework is built in a way so the tests can easily be run in parallel by tweaking the testng.xml a little. Adding the line 
`<suite name="Suite" parallel="methods">` is all the change that is needed to run the tests in parallel
