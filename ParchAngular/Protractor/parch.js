

browser.ignoreSynchronization = true;

describe("Parch Login and Registration test suite",function(){

    it("Should go to Parch Login page",function(){
        browser.get("http://ec2-18-207-247-77.compute-1.amazonaws.com:4200/login")
        expect(browser.getTitle()).toBe("Parch")
    });
    /* it("Should register a new user",function(){
        element(by.name("Email")).sendKeys("email@domain.com")
        element(by.name("NewUsername")).sendKeys("Grover")
        element(by.name("NewPassword")).sendKeys("Clevland")
        magnifiyingglass = element(by.id("reg"))
        magnifiyingglass.click()
        expect(element(by.name("snackbarMessage"))).toBe("You successfully registered your account!")
    }); */
    it("Should login with my test account",function(){
        element(by.name("Username")).sendKeys("ChrisPrime")
        element(by.name("Password")).sendKeys("Prime1")
        magnifiyingglass = element(by.id("log"))
        magnifiyingglass.click()
        browser.sleep(2000)
        expect(element(by.id("currentUsernameDisplay"))).toBe("ChrisPrime")
    });
    it("Should logout of Parch",function(){
        element(by.name("logout"))
        magnifiyingglass = element(by.name("logout"))
        magnifiyingglass.click()
        expect(element(by.id("reg")))
    });
    it("Should fail to register",function(){
        element(by.name("Email")).sendKeys("email@domain.com")
        element(by.name("NewUsername")).sendKeys("ChrisPrime")
        element(by.name("NewPassword")).sendKeys("Prime1")
        magnifiyingglass = element(by.id("reg"))
        magnifiyingglass.click()
        expect(element(by.name("snackbarMessage"))).toBe("Something went wrong with registration") 
    })
    it("Should fail to login",function(){
        element(by.name("Username")).sendKeys("ChrisPrime")
        element(by.name("Password")).sendKeys("Whatup")
        magnifiyingglass = element(by.id("log"))
        magnifiyingglass.click()
        expect(element(by.name("snackbarMessage"))).toBe("That was not a valid login") 
    })
})


