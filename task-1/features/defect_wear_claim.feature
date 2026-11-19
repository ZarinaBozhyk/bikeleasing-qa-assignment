@defect_wear
Feature: Route defect and wear claims to warranty
  Defect and wear claims follow the warranty route instead of an insurance payment.

  Background:
    Given the claim exists in the system
    And the user's contract is valid
    And the reported bike is the bike insured under that contract

  Rule: Both defect and wear claims are routed out to warranty

    @DW-01
    Scenario Outline: Route a claim for <claim_type> out of insurance
      Given the bike is reported as "broken"
      And the bike has <claim_type>
      And evidence of the bike's condition has been submitted
      When the claim is assessed
      # TODO: Confirm that defect and wear claims initially use the "Damage" category.
      Then the claim category is "Damage"
      # TODO: Clarify how defect and wear are identified and which evidence is required.
      # Assumption: This scenario checks the reported result only;
      # diagnosis and evidence-verification methods are outside its scope.
      And the evidence check confirms that the bike has "<claim_type>"
      And the claim outcome is "Routed out"
      And the claim is directed to the warranty route
      And the claim is "not paid" by insurance

      Examples:
        | claim_type |
        | a defect   |
        | wear       |