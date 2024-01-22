Feature:
Como desarrollador de aplicaciones de banco azteca requiero realizar test sobre mi patron de shared flow en un proxy dummy, para validar su funcionamiento

Scenario: Verificar que obtenga la información a un escenario ok del proxy
    Given I have valid client TLS configuration
    When I GET `apigeeDomain`/sf-cifrado-acceso/`deploymentSuffix`
    Then response code should be 200
    And response body should be valid json
    
