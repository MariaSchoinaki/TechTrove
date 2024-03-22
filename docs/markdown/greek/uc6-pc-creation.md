# ΠΧ6 Δημιουργία Σύνθεσης

**Πρωτεύων Actor**: Πελάτης 

**Ενδιαφερόμενοι:**
- **Πελάτης**:  Θέλει να μπορεί να δημιουργεί λειτουργικές συνθέσεις και μετέπειτα να μπορεί τις αγοράζει.

**Προϋποθέσεις**: Ο Πελάτης έχει εκτελέσει με επιτυχία τη περίπτωση χρήσης "Ταυτοποίηση Χρήστη". 

## Βασική Ροή

### Α) Δημιουργία Σύνθεσης
1. Ο Πελάτης πλοηγείται στη εφαρμογή και επιλέγει "Δημιουργία Σύνθεσης".
2. Το Σύστημα εμφανίζει τις κατηγορίες εξαρτημάτων.(μπάρα κατηγοριών)
3. Ο Πελάτης πλοηγείται στις κατηγορίες και επιλέγει 1.
4. Το Σύστημα ελέγχει την συμβατότητα των εξαρτημάτων που θα εμφανίσει, με αυτά που ο χρήστης έχει ήδη επιλέξει.
5. Το Σύστημα εμφανίζει τα προϊόντα της κατηγορίας, που είναι και συμβατά με τα ήδη επιλεγμένα εξαρτήματα του χρήστη.(φίλτρο)
6. Ο Πελάτης πλοηγείται στα προϊόντα και επιλέγει εκείνο που επιθυμεί.
7. Το Σύστημα αποθηκεύει προσωρινά την επιλογή του πελάτη.
8. Τα βήματα 3-7 επαναλαμβάνονται για όσο ο Πελάτης προσθέτει προϊόντα στην σύνθεσή του.
9. Ο Πελάτης επιλέγει "Προσθήκη στο καλάθι".
10. Το Σύστημα ελέγχει αν η σύνθεση είναι πλήρης.(αν έχει τα βασικά εξαρτήματα για να είναι η σύνθεση λειτουργική)
11. Το Σύστημα προσθέτει τη σύνθεση στο καλάθι.
12. Το Σύστημα εμφανίζει μήνυμα επιτυχούς προσθήκης.

</br>

**Εναλλακτικές ροές**


*3α. / 6α. / 9α. Ο Πελάτης αποφασίζει να μην προχωρήσει στη δημιουργία της σύνθεσης του.*
1. Ο Πελάτης επιλέγει "Ακύρωση".
2. Η ΠΧ τερματίζει.
---

*3β. Ο Πελάτης επιλέγει κατηγορία που έχει ήδη επιλέξει προϊόν*
1. Το Σύστημα ελέγχει την συμβατότητα των εξαρτημάτων που θα εμφανίσει, με αυτά που ο χρήστης έχει ήδη επιλέξει.
2. Το Σύστημα εμφανίζει τα προϊόντα της κατηγορίας, που είναι και συμβατά με τα ήδη επιλεγμένα εξαρτήματα του χρήστη, καθώς και το επιλεγμένο προϊόν, μαρκαρισμένο.(φίλτρο)
3. Η διαδικασία επιστρέφει στο βήμα 6 της βασικής ροής.
---

*6β. Ο Πελάτης αποφασίζει να προχωρήσει στο επόμενο βήμα, χωρίς να επιλέξει εξάρτημα.*
1. Η διαδικασία επιστρέφει στο βήμα 3 της βασικής ροής.
---

*9β. Ο Πελάτης επιθυμεί να αποθηκεύσει την σύνθεση του, για μεταγενέστερη αγορά.*
1. Ο Πελάτης επιλέγει "Αποθήκευση".
2. Το Σύστημα αποθηκεύει τη σύνθεση στα αποθηκευμένα του χρήστη.
3. Η ΠΧ τερματίζει.
---

*11α. Η σύνθεση δεν είναι πλήρης.*
1. Το Σύστημα εμφανίζει μήνυμα με τα υποχρεωτικά εξαρτήματα που λείπουν από τη σύνθεση.
2. Ο Πελάτης επιλέγει "ΟΚ".
3. Η διαδικασία επιστρέφει στο βήμα 3 της βασικής ροής.
---

*1α. Ο Πελάτης θέλει να κάνει προεπισκόπιση μιας αποθηκευμένης του σύνθεσης.*
1. Ο Πελάτης πλοηγείται στην λίστα με τα "Αποθηκευμένα" του.
2. Ο Πελάτης επιλέγει μια σύνθεση.
3. Το Σύστημα εμφανίζει τις κατηγορίες εξαρτημάτων.
4. Ο Πελάτης:
    * 4α. επιθυμεί τροποποίηση της σύνθεσής του.
        1. Η Διαδικασία επιστρέφει στο βήμα 3 της βασικής ροής.
    * 4β. επιθυμεί να προσθέσει την ήδη υπάρχουσα σύνθεσή του στο καλάθι.
        1. Η Διαδικασία επιστρέφει στο βήμα 9 της βασικής ροής.
---

*Σε οποιοδήποτε σημείο το λογισμικό καταρρέει.
1. Ο Χρήστης επανεκκινεί την εφαρμογή. 
2. Η διαδικασία επιστρέφει στο βήμα 2 της ΠΧ1.

## Διάγραμμα Δραστηριότητας
![Διάγραμμα Δραστηριότητας](../uml/requirements/activity-pc-creation.png)