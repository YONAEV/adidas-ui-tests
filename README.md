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

## Automated Use Cases
### Test 1 (Test_ForgottenPasswordFieldValidation)

1. Open adidas.fi
2. Click login link
3. Click Forgotten password link
4. Click reset button without entering email or submitting captcha
5. Validate that correct error messages show up

### Test 2 (Test_NewsLetterSignUp)

1. Open adidas.fi
2. Scroll down to the email address field
3. Enter a random email address and click submit button
4. Validate that the page has email address pre populated and it is the same as it was entered in step 3
5. Click Sign Up button
6. Validate that confirmation message section shows up
 
 ### Test 3 (Test_SearchForWomensJacket)
 
1. Open adidas.fi
2. Enter "jackets" in search text box
3. Filter for WOMENS jackets
4. Filter for product type as jackets
5. Filter for sport as "Yoga"
6. Select a product with subtitle "Women adidas by Stella McCartney" and open it
7. Validate that correct product was displayed
8. Add the product to wishlit
9. Validate that the number on the wishlist icon on the top nav now shows 1
 
 
