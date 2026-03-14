package com.juanmorschrott.api;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.core.ApplicationModules;

class ApplicationTests {

    @Test
    void verifiesModularStructure() {
        ApplicationModules.of(Application.class).verify();
    }

    @Test
    void writeDocumentationSnippets() {
        new Documenter(ApplicationModules.of(Application.class))
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }

}
