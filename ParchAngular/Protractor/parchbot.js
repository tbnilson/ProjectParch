//testing post editing

browser.ignoreSynchronization = false;

describe("Parch messaging bots",function(){

    it("Should go to Parch Login page",function(){
        browser.get("http://ec2-18-207-247-77.compute-1.amazonaws.com:4200/login")
        expect(browser.getTitle()).toBe("Parch")
    });
    it("Should login as a test account",function(){
        element(by.name("Username")).sendKeys("test1")
        element(by.name("Password")).sendKeys("testpass")
        magnifiyingglass = element(by.id("log"))
        magnifiyingglass.click()
        browser.sleep(2000)
        expect(element(by.id("currentUsernameDisplay")))
    });
    it("Should logout of Parch",function(){
        element(by.name("logout"))
        magnifiyingglass = element(by.name("logout"))
        magnifiyingglass.click()
        expect(element(by.id("reg")))
    });
})
describe("Parch message bot 2",function(){
    it("Should go to Parch Login page",function(){
        browser.get("http://ec2-18-207-247-77.compute-1.amazonaws.com:4200/login")
        expect(browser.getTitle()).toBe("Parch")
    });
    it("Should login as a test account",function(){
        element(by.name("Username")).sendKeys("test2")
        element(by.name("Password")).sendKeys("testpass")
        magnifiyingglass = element(by.id("log"))
        magnifiyingglass.click()
        browser.sleep(2000)
        expect(element(by.id("currentUsernameDisplay")))
    });
    it("Should logout of Parch",function(){
        element(by.name("logout"))
        magnifiyingglass = element(by.name("logout"))
        magnifiyingglass.click()
        expect(element(by.id("reg")))
    });
})