import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipSirAdminSharedModule } from 'app/shared';
import {
  OpeningHoursSpecificationComponent,
  OpeningHoursSpecificationDetailComponent,
  OpeningHoursSpecificationUpdateComponent,
  OpeningHoursSpecificationDeletePopupComponent,
  OpeningHoursSpecificationDeleteDialogComponent,
  openingHoursSpecificationRoute,
  openingHoursSpecificationPopupRoute
} from './';

const ENTITY_STATES = [...openingHoursSpecificationRoute, ...openingHoursSpecificationPopupRoute];

@NgModule({
  imports: [JhipSirAdminSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OpeningHoursSpecificationComponent,
    OpeningHoursSpecificationDetailComponent,
    OpeningHoursSpecificationUpdateComponent,
    OpeningHoursSpecificationDeleteDialogComponent,
    OpeningHoursSpecificationDeletePopupComponent
  ],
  entryComponents: [
    OpeningHoursSpecificationComponent,
    OpeningHoursSpecificationUpdateComponent,
    OpeningHoursSpecificationDeleteDialogComponent,
    OpeningHoursSpecificationDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipSirAdminOpeningHoursSpecificationModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
