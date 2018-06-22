import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { CustomProcessComponentsPage, CustomProcessUpdatePage } from './custom-process.page-object';

describe('CustomProcess e2e test', () => {
    let navBarPage: NavBarPage;
    let customProcessUpdatePage: CustomProcessUpdatePage;
    let customProcessComponentsPage: CustomProcessComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load CustomProcesses', () => {
        navBarPage.goToEntity('custom-process');
        customProcessComponentsPage = new CustomProcessComponentsPage();
        expect(customProcessComponentsPage.getTitle()).toMatch(/jHipster5TestApp.customProcess.home.title/);
    });

    it('should load create CustomProcess page', () => {
        customProcessComponentsPage.clickOnCreateButton();
        customProcessUpdatePage = new CustomProcessUpdatePage();
        expect(customProcessUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.customProcess.home.createOrEditLabel/);
        customProcessUpdatePage.cancel();
    });

    it('should create and save CustomProcesses', () => {
        customProcessComponentsPage.clickOnCreateButton();
        customProcessUpdatePage.setNameInput('name');
        expect(customProcessUpdatePage.getNameInput()).toMatch('name');
        customProcessUpdatePage.setInterfaceFqnInput('interfaceFqn');
        expect(customProcessUpdatePage.getInterfaceFqnInput()).toMatch('interfaceFqn');
        customProcessUpdatePage
            .getTemplateInput()
            .isSelected()
            .then(selected => {
                if (selected) {
                    customProcessUpdatePage.getTemplateInput().click();
                    expect(customProcessUpdatePage.getTemplateInput().isSelected()).toBeFalsy();
                } else {
                    customProcessUpdatePage.getTemplateInput().click();
                    expect(customProcessUpdatePage.getTemplateInput().isSelected()).toBeTruthy();
                }
            });
        customProcessUpdatePage.setSourceInput('source');
        expect(customProcessUpdatePage.getSourceInput()).toMatch('source');
        customProcessUpdatePage.setSchedulingInput('scheduling');
        expect(customProcessUpdatePage.getSchedulingInput()).toMatch('scheduling');
        customProcessUpdatePage.save();
        expect(customProcessUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
