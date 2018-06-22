import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JHipster5TestCustomProcessModule } from './custom-process/custom-process.module';
import { JHipster5TestEventLogModule } from './event-log/event-log.module';
import { JHipster5TestMessageTemplateModule } from './message-template/message-template.module';
import { JHipster5TestCardTypeModule } from './card-type/card-type.module';
import { JHipster5TestIdentityProfileModule } from './identity-profile/identity-profile.module';
import { JHipster5TestCertificateTemplateModule } from './certificate-template/certificate-template.module';
import { JHipster5TestCertificateAuthorityModule } from './certificate-authority/certificate-authority.module';
import { JHipster5TestDirectoryServerModule } from './directory-server/directory-server.module';
import { JHipster5TestCertificateModule } from './certificate/certificate.module';
import { JHipster5TestPersonModule } from './person/person.module';
import { JHipster5TestIdentityModule } from './identity/identity.module';
import { JHipster5TestPhysicalCardModule } from './physical-card/physical-card.module';
import { JHipster5TestLogicalCardModule } from './logical-card/logical-card.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JHipster5TestCustomProcessModule,
        JHipster5TestEventLogModule,
        JHipster5TestMessageTemplateModule,
        JHipster5TestCardTypeModule,
        JHipster5TestIdentityProfileModule,
        JHipster5TestCertificateTemplateModule,
        JHipster5TestCertificateAuthorityModule,
        JHipster5TestDirectoryServerModule,
        JHipster5TestCertificateModule,
        JHipster5TestPersonModule,
        JHipster5TestIdentityModule,
        JHipster5TestPhysicalCardModule,
        JHipster5TestLogicalCardModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JHipster5TestEntityModule {}
