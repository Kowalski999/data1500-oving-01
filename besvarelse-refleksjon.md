# Besvarelse på refleksjonspørsmålene

Ditt brukernavn på Oslomet: lulin7681

## Refleksjonsspørsmål Oppgave 1 

S1: Hva er fordelene med å lagre data i et slikt format (CSV - Comma Separated Values)?
- Ditt svar: Fungerer som en standard. Man vet at verdiene er skilt med komma så man trenger ikke å lete etter seperator i filen
  
S2: Hva skjer hvis et av feltene, for eksempel et navn, inneholder et komma? Hvilke problemer skaper det for din parsing-logikk?
- Ditt svar: Hvis et felt inneholder et komma vil det ødelegge parsing logikken ved å dele opp et felt i to felter og dermed misplassere verdiene.

S3: Beregning av lagringsbehov

S3.1: La oss lage en forenklet modell for vår fil. Anta at hvert tegn (character) er 1 byte (dette er en forenkling, f.eks. husk UTF-8). Regn ut den omtrentlige størrelsen i bytes for én linje i din fil studenter.csv (f.eks., en linje er 101,Mickey,CS). Ikke glem å telle med kommaene og et tegn for linjeskift.
- Ditt svar: Linjene i filen er på henholdsvis 14, 13, 14 og 15 med tegn, inlludert linejskift, som gir et gjennomsnitt på 14 tegn. Hvis vi antar at hvert tegn er 1 byte, vil den omtrentlige størrelsen på en linje i filen min være 14 bytes. 

S3.2 Basert på denne beregningen, hva ville den teoretiske filstørrelsen vært for 1 million studenter? Hvor stor for 1 milliard studenter? Uttrykk svarene i MB, GB eller TB.
- Ditt svar: For 1 million studenter vil filstørrelsen være omtrent 13.35 MB og for 1 milliard vil den være omtrent 13.04 GB

## Refleksjonsspørsmål Oppgave 2

S1: Sammenlign tidene fra de tre testene (liten fil, tidlig treff i stor fil, og sent treff i stor fil). Hva forteller dette deg om ytelsen til en lineær skanning?
- Ditt svar: Tidden et lineært søk tar avhenger av hvor mange elementer man må lese gjennom før man kommer til det man leter etter. Det betyr at jo senere posten ligger og jo større filen eer desto lengre tid tar det.
  
S2: Hvilken rolle spiller **BufferedWriter** (en klasse i Java standard I/O biblioteket), hvis den brukes i datageneratoren? Forklar konsepter fra forelesningen, som IO Blocks og bufring.
- Ditt svar: BufferedWriter er viktig fordi den brukes til bufring av data. I stedet for at hver skriveoperasjon sendes direkte til disk, samler BufferedWriter data i minnet og skriver dem i større pakker (I/O-blokker). Dette reduserer antall diskoperasjoner og gjør I/O mye raskere enn om man skulle skrevet hver linje direkte til disk individuelt.

S3: Teoretisk lesetid:  

S3.1 Hva blir den faktiske filstørrelsen hvis det er 1 millioner rader i filen med brukerdata (format spesifisert i Oppgave 2.1).
- Ditt svar: Første linje vil ha 36 tegn inkludert newline, imens siste linje vil ha 53 tegn inkludert newline. Dette er fordi tallet øker på 3 steder i linjen. Jeg velger derfor å ta gjennomsnittet mellom første og siste linje for å få den beste tilnærmingen som gir et svar på 42,44 MB.
  
S3.2 Bruk tabellen i sliden *“Lagringsmedier – fra rask til billig”* fra forelesningen. Hva ville den teoretiske tiden vært for å lese hele denne filen (en full "scan") fra henholdsvis en **HDD (Hard Disk)** og en **High-end SSD**?
- Ditt svar: HDD: 42.4 / 150 = 0.28 sekunder | High-End SSD (NVMe): 42.4 / 3000 = 0.014 sekunder

S3.3 Anta at følgende formel gjelder: 
```
Total Time \= AccessLatency \* M \+ DataSize/ScanThroughput 
```
For en full, sekvensiell skanning kan vi anta at M=1 (dataene leses som én stor, sammenhengende blokk). Sammenlign de teoretiske tidene med den faktiske tiden du målte i Oppgave 2.3 (Stor fil, sent treff). Hvorfor kan tidene være forskjellige? (Hint: Tenk på operativsystemets fil-caching, CPU-bruk for parsing i Java, etc.).
- Ditt svar: Selv med modderne NVMe SSD med opptil 6000 i hastighet og en kraftig Ryzen 7 9700x cpu, var den kalkulerte tiden med 3000 i hastighet, 14ms, vesentlig mye lavere en den virkelige tiden på 78ms. Tiden øker på grunn av JVM - Overhead samt logikk og parsing og systemets håndtering av I/O. Dette kan indikere at script logikk er viktigere enn top notch datavare. 

## Refleksjonsspørsmål Oppgave 3

S1: Hvorfor er det mer effektivt å lese **studenter.csv** og **kurs.csv** inn i HashMap først, i stedet for å søke gjennom filene for hver eneste linje i **paameldinger.csv**?
- Ditt svar: Ved lineær skanning øker tiden det tar lineært med antall linjer (O(n)). Ved å bruke en HashMap kan vi redusere søketiden til konstant tid (O(1)), spesielt for større datasett. 
  
S2: Lagringsplass og minnebruk:

La oss si vi har 1 million studenter, 1000 kurs, og hver student tar i gjennomsnitt 5 kurs (totalt 5 millioner påmeldinger).

**Scenario A (Ikke-normalisert):** Vi lagrer \`studentID, fornavn, etternavn, kursID, kursnavn\` på hver linje. Anta at studentinfo trenger 100 bytes og kursinfo trenger 50 bytes for lagring. Hva blir den totale filstørrelsen for de 5 millioner påmeldingene?
- Ditt svar: 100 + 50 = 150 | 150 * 5 000 000 = 750 000 000 Bytes = 715.3 MB 

**Scenario B (Normalisert):** Vi har tre filer. Beregn den totale størrelsen for **studenter.csv** (1M rader), **kurs.csv** (1000 rader), og **paameldinger.csv** (5M rader, inneholder kun to ID-er, f.eks. 8 bytes totalt per rad).  
Sammenlign total lagringsplass i Scenario A og B. Hvor mye plass sparer vi på normalisering?
- Ditt svar: 1 000 000 * 100 = 100 000 000 Bytes | 1000 * 50 = 50 000 Bytes | 5 000 000 * 8 = 40 000 000 Bytes | 100 000 000 + 50 000 + 40 000 000 = 140 050 000 Bytes = 133.6 MB | 715.3 - 133.6 = 581.7

S3: I din Java-løsning lastet du data inn i RAM. Se på tabellen i sliden *“Lagringsmedier – fra rask til billig”*. Hva er den typiske kostnaden per TB for RAM sammenlignet med SSD og HDD? Hvorfor er det en viktig vurdering om en datastruktur (som din HashMap-indeks) passer i RAM eller ikke?
- Ditt svar: Ifølge tabellen er RAM rundt 30 ganger dyrere enn SSD. Det er viktig at HashMap får plass i RAM, fordi CPU kun kan arbeide effektivt med data som ligger i hovedminnet. Hvis datastrukturen ikke får plass i RAM og må hentes fra disk, må den pages inn i RAM, noe som gir betydelig tregere ytelse.

## Refleksjonsspørsmål Oppgave 4 

S1: Hva er den observerte tidsforskjellen mellom det lineære søket og det indekserte oppslaget? Hvorfor er forskjellen så enorm?
- Ditt svar: Forskjellen er på 78 ms. Forskjellen er enorm fordi filen er såpass stor at det tar mye lengre tid å søke gjennom alle poster for å finne den rette, enn å bare slå opp på rett "adresse" med en gang. 
- 
S2: Hvilken lagringstype (RAM, SSD, HDD) opererer HashMap\-oppslaget på, og hva er den tilhørende “Aksesstid” ifølge tabellen fra forelesningene i sliden *“Lagringsmedier – fra rask til billig”*?
- Ditt svar: HashMap oppslaget opererer på RAM med aksesstid tilnærmet lik 50 - 100 nanosekunder. 
- 
Kostnaden ved en indeks:  
S3: Hvor stor ble din brukere.idx\-fil? Sammenlign størrelsen med brukere.csv.
- Ditt svar: brukere.idx ble rundt 38 MB ifølge intelliJ, imens brukere.csv ble omtrent 50 MB ifølge intelliJ, så altså 12 MB mindre. 
  
S4: En indeks er ikke gratis, den tar opp ekstra lagringsplass. Hvis indeksen din var 200 MB, hva ville den kostet å lagre den på en HDD? Hva med en SSD? Bruk tallene fra forelesningen, slide *“Lagringsmedier – fra rask til billig”*. Er dette en akseptabel kostnad for den ytelsesgevinsten du får? (Hint: *“tid/plass trade-off”*)
- Ditt svar: HDD: 250 / (1000*1000) * 200 = 0.05 kr | SSD 1000 / (1000*1000) * 200 = 0.2 | Det betyr at lagring på SSD er omtrent 4 ganger dyrere. 

## Vedlegg Lagringshierarki: Hastighet, kostnad og kapasitet
Tabellen er relevant for å besvare flere av refleksjonsspørsmålene. Tabellen er kopiert fra `https://cs145-bigdata.web.app/Section2-Systems/storage-paging.html`.
|Storage Level 	|Access Latency 	|Throughput 	|Cost per TB 	|Typical Capacity 	|Use Case|
|-|-|-|-|-|-|
|CPU Registers 	|1 cycle 	|- 	|- 	|< 1KB 	|Immediate values|
|L1/L2 Cache 	|1-10ns 	|- 	|- 	|64KB - 8MB 	|Hot instructions|
|RAM (Buffer Pool) 	|100ns 	|100 GB/s 	|$3,500 	|16GB 	|Working set pages|
|SSD 	|10μs 	|5 GB/s 	|$75 	|512GB 	|Active tables|
|HDD 	|10ms 	|100 MB/s 	|$25 	|4TB 	|Cold storage|
|Network Storage 	|1μs 	|10 GB/s 	|Variable 	|∞ 	|Distributed cache|

SLUTT.
