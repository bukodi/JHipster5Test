import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { CertificateTemplateComponentsPage, CertificateTemplateUpdatePage } from './certificate-template.page-object';

describe('CertificateTemplate e2e test', () => {
    let navBarPage: NavBarPage;
    let certificateTemplateUpdatePage: CertificateTemplateUpdatePage;
    let certificateTemplateComponentsPage: CertificateTemplateComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load CertificateTemplates', () => {
        navBarPage.goToEntity('certificate-template');
        certificateTemplateComponentsPage = new CertificateTemplateComponentsPage();
        expect(certificateTemplateComponentsPage.getTitle()).toMatch(/jHipster5TestApp.certificateTemplate.home.title/);
    });

    it('should load create CertificateTemplate page', () => {
        certificateTemplateComponentsPage.clickOnCreateButton();
        certificateTemplateUpdatePage = new CertificateTemplateUpdatePage();
        expect(certificateTemplateUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.certificateTemplate.home.createOrEditLabel/);
        certificateTemplateUpdatePage.cancel();
    });

    /* it('should create and save CertificateTemplates', () => {
        certificateTemplateComponentsPage.clickOnCreateButton();
        certificateTemplateUpdatePage.setNameInput('name');
        expect(certificateTemplateUpdatePage.getNameInput()).toMatch('name');
        certificateTemplateUpdatePage.typeSelectLastOption();
        certificateTemplateUpdatePage.keyGenerationSelectLastOption();
        certificateTemplateUpdatePage.keyArchivationSelectLastOption();
        certificateTemplateUpdatePage.caSelectLastOption();
        certificateTemplateUpdatePage.ca2SelectLastOption();
        certificateTemplateUpdatePage.save();
        expect(certificateTemplateUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
