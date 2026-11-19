# Coverage matrix

This table describes the written scenarios.
It does not report test execution results.

| ID | Case | Expected result |
| --- | --- | --- |
| IN-01 | Existing claim, valid contract and matching bike | All three intake conditions are confirmed |
| IN-02 | Claim does not exist | The existence check identifies the missing claim |
| IN-03 | Contract is invalid | The contract check identifies the invalid contract |
| IN-04 | Reported bike does not match the insured bike | The bike check identifies the mismatch |
| TH-01 | Stolen bike was not locked properly | Theft category; Rejected; no insurance payment |
| TH-02 | Covered theft is approved, paid and followed by recovery | Theft category; Approved; payment and replacement; Recovered when the original bike is reported as found |
| DA-01 | Partial settlement with covered repair costs and wear parts | Damage category; Partial; eligible repair costs paid; wear parts excluded |
| DA-02 | Damaged bike is not worth repairing | Damage category; Total loss; insurance payment |
| DW-01 — a defect | Evidence identifies a defect | Routed out; warranty route; no insurance payment |
| DW-01 — wear | Evidence identifies wear | Routed out; warranty route; no insurance payment |

## Limits

- Intake scenarios check the condition results. The next action after a failed check needs clarification.
- Detailed evidence rules, valuation rules and payment calculations need clarification.
- The initial Damage category used in the defect/wear feature needs confirmation.
- Warranty acceptance and financial consequences after recovery are not specified.

See [clarification questions](questions.md).