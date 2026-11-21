# Coverage matrix

This table describes the written scenarios.
It does not report test execution results.

| ID | Case | Expected result |
| --- | --- | --- |
| IN-01 | Existing claim, valid contract and matching bike | All three intake conditions are confirmed; the claim proceeds to triage |
| IN-02 | Claim does not exist | The existence check identifies the missing claim; the claim does not proceed to triage |
| IN-03 | Contract is invalid | The contract check identifies the invalid contract; the claim does not proceed to triage |
| IN-04 | Reported bike does not match the insured bike | The bike check identifies the mismatch; the claim does not proceed to triage |
| TH-01 | Stolen bike was not locked properly | Theft category; Rejected; no insurance payment |
| TH-02 | Covered theft of a properly locked bike | Theft category; Approved; paid; new bike provided |
| TH-03 | Stolen bike is found after replacement | Recovered |
| DA-01 | Partial settlement with covered repair costs and wear parts | Damage category; Partial; eligible repair costs paid; wear parts excluded |
| DA-02 | Damaged bike is not worth repairing | Damage category; Total loss; insurance payment |
| DW-01 — a defect | Evidence identifies a defect | Defect or wear category; Routed out; warranty route; no insurance payment |
| DW-01 — wear | Evidence identifies wear | Defect or wear category; Routed out; warranty route; no insurance payment |

## Limits

- Intake scenarios assume that a failed check stops the claim before triage. What happens next (rejection, request for documents, manual review) needs clarification.
- Detailed evidence rules, valuation rules and payment calculations need clarification.
- Warranty acceptance and financial consequences after recovery are not specified.

See [clarification questions](questions.md).