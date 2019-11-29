describe('RennesGo Test', function () {

    beforeEach(function () {
        browser.get('http://localhost:4200/');
    });

    it('should be titled Rennesgo', function () {
        expect(browser.getTitle()).toEqual('Rennesgo');
    });

    it('should add the c4 line to favorites', function () {
        element(by.id('username')).sendKeys('name');
        element(by.id('password')).sendKeys('pwd');
        element(by.id('create')).click();
        element(by.id('login')).click();
        element(by.className('C4')).click();
        element(by.id('preferred')).click();
        expect(element(by.className('C4')).isPresent()).toBeTruthy();
    });
});