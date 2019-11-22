describe('RennesGo Test', function () {

    beforeEach(function () {
        browser.get('http://localhost:4200/');
    });

    it('should be titled Rennesgo', function () {
        expect(browser.getTitle()).toEqual('Rennesgo');
    });
});