import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { CardTypeComponentsPage, CardTypeUpdatePage } from './card-type.page-object';

describe('CardType e2e test', () => {
    let navBarPage: NavBarPage;
    let cardTypeUpdatePage: CardTypeUpdatePage;
    let cardTypeComponentsPage: CardTypeComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load CardTypes', () => {
        navBarPage.goToEntity('card-type');
        cardTypeComponentsPage = new CardTypeComponentsPage();
        expect(cardTypeComponentsPage.getTitle()).toMatch(/jHipster5TestApp.cardType.home.title/);
    });

    it('should load create CardType page', () => {
        cardTypeComponentsPage.clickOnCreateButton();
        cardTypeUpdatePage = new CardTypeUpdatePage();
        expect(cardTypeUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.cardType.home.createOrEditLabel/);
        cardTypeUpdatePage.cancel();
    });

    it('should create and save CardTypes', () => {
        cardTypeComponentsPage.clickOnCreateButton();
        cardTypeUpdatePage.setNameInput('name');
        expect(cardTypeUpdatePage.getNameInput()).toMatch('name');
        cardTypeUpdatePage.save();
        expect(cardTypeUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
