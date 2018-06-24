import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { PersonComponentsPage, PersonUpdatePage } from './person.page-object';

describe('Person e2e test', () => {
    let navBarPage: NavBarPage;
    let personUpdatePage: PersonUpdatePage;
    let personComponentsPage: PersonComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load People', () => {
        navBarPage.goToEntity('person');
        personComponentsPage = new PersonComponentsPage();
        expect(personComponentsPage.getTitle()).toMatch(/jHipster5TestApp.person.home.title/);
    });

    it('should load create Person page', () => {
        personComponentsPage.clickOnCreateButton();
        personUpdatePage = new PersonUpdatePage();
        expect(personUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.person.home.createOrEditLabel/);
        personUpdatePage.cancel();
    });

    it('should create and save People', () => {
        personComponentsPage.clickOnCreateButton();
        personUpdatePage.setNameInput('name');
        expect(personUpdatePage.getNameInput()).toMatch('name');
        personUpdatePage.setTitleInput('title');
        expect(personUpdatePage.getTitleInput()).toMatch('title');
        personUpdatePage.setFirstNameInput('firstName');
        expect(personUpdatePage.getFirstNameInput()).toMatch('firstName');
        personUpdatePage.setLastNameInput('lastName');
        expect(personUpdatePage.getLastNameInput()).toMatch('lastName');
        personUpdatePage.setIdCardInput('idCard');
        expect(personUpdatePage.getIdCardInput()).toMatch('idCard');
        personUpdatePage.setZipCodeInput('zipCode');
        expect(personUpdatePage.getZipCodeInput()).toMatch('zipCode');
        personUpdatePage.setCityInput('city');
        expect(personUpdatePage.getCityInput()).toMatch('city');
        personUpdatePage.setAddressInput('address');
        expect(personUpdatePage.getAddressInput()).toMatch('address');
        personUpdatePage.setEmailInput('email');
        expect(personUpdatePage.getEmailInput()).toMatch('email');
        personUpdatePage.setPhoneInput('phone');
        expect(personUpdatePage.getPhoneInput()).toMatch('phone');
        personUpdatePage.setAdditionalInfoInput('additionalInfo');
        expect(personUpdatePage.getAdditionalInfoInput()).toMatch('additionalInfo');
        personUpdatePage.userSelectLastOption();
        personUpdatePage.save();
        expect(personUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
