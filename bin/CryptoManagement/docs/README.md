# **Document de Conception - CryptoManagement**  
**Éléments pour les diagrammes UML (Cas d'utilisation, Classes, Séquence)**  

---

## **1. Acteurs du Système**  

**Acteur**: Utilisateur  
**Rôle**: Utilisateur standard (peut gérer son compte et ses portefeuilles).  

**Acteur**: Administrateur  
**Rôle**: (Optionnel) Peut gérer tous les utilisateurs/portefeuilles.  

**Acteur**: Système Externe  
**Rôle**: (Futur) API de cryptomonnaies pour les prix en temps réel.
---

## **2. Cas d’Utilisation (Use Cases)**  

### **UC1 : Gérer le Compte Utilisateur**  
- **Inscription** (Saisie email, username, mot de passe → validation)  
- **Connexion** (Vérification credentials → ouverture session)  
- **Déconnexion** (Terminaison de la session)  

### **UC2 : Gérer les Portefeuilles**  
- **Créer un portefeuille** (Nom + association à l’utilisateur)  
- **Supprimer un portefeuille** (Avec confirmation)  
- **Consulter le solde** (Valeur totale calculée)  

### **UC3 : Enregistrer une Transaction**  
- **Ajouter une transaction** (Type: Achat/Vente/Envoi, crypto, quantité, prix unitaire)  
- **Annuler une transaction** (Si statut = "En attente")  

### **UC4 : Simuler un Échange**  
- **Sélectionner cryptos source/cible**  
- **Saisir le montant** → Calcul automatique du taux et du montant converti  

### **UC5 : Visualiser les Statistiques**  
- **Graphique d’évolution** (Valeur totale sur 30 jours)  
- **Export PDF/CSV** (Historique des transactions)  

---

## **3. Diagramme de Classes**  

### **Classes Principales**  

#### **User**  
```java
class User {
    -id: int  
    -username: String  
    -passwordHash: String  
    -email: String  
    -wallets: List<Wallet>  
    +addWallet(w: Wallet): void  
    +validatePassword(pwd: String): boolean  
}
```

#### **Wallet**  
```java
class Wallet {
    -id: int  
    -name: String  
    -userId: int  
    -totalValue: BigDecimal  
    -transactions: List<Transaction>  
    +addTransaction(t: Transaction): void  
    +updateTotalValue(): void  
}
```

#### **Transaction**  
```java
class Transaction {
    -id: int  
    -date: LocalDateTime  
    -type: Enum<ACHAT, VENTE, ENVOI>  
    -quantity: BigDecimal  
    -pricePerUnit: BigDecimal  
    -walletId: int  
    -cryptoId: int  
    +getTotalAmount(): BigDecimal  
}
```

#### **Crypto**  
```java
class Crypto {
    -id: int  
    -symbol: String  
    -name: String  
}
```

### **Associations**  
- **User** → **Wallet** (1..* → "possède")  
- **Wallet** → **Transaction** (1..* → "contient")  
- **Transaction** → **Crypto** (1 → 1 → "concerne")  

---

## **4. Diagramme de Séquence (Exemple : UC3 - Ajouter une Transaction)**  

### **Acteurs** : Utilisateur, Système  
1. **Utilisateur** : Ouvre le formulaire de transaction  
2. **Système** : Affiche la liste des cryptos disponibles  
3. **Utilisateur** : Sélectionne une crypto, saisit quantité/prix  
4. **Système** : Valide les données (vérifie solde si vente)  
5. **Système** : Enregistre en base via `TransactionDAO.save()`  
6. **Système** : Met à jour le solde du portefeuille (`Wallet.updateTotalValue()`)  
7. **Système** : Confirme l’enregistrement à l’utilisateur  

---

## **5. Contrôleurs et Services**  

### **AuthController**  
```java
class AuthController {
    -userDAO: UserDAO  
    +login(username: String, password: String): boolean  
    +register(user: User): boolean  
}
```

### **WalletController**  
```java
class WalletController {
    -walletDAO: WalletDAO  
    -transactionDAO: TransactionDAO  
    +createWallet(userId: int, name: String): Wallet  
    +addTransaction(walletId: int, t: Transaction): boolean  
}
```

---

## **6. Diagramme de Composants**  
- **Module Authentification** : Gère UC1  
- **Module Portefeuilles** : Gère UC2-UC3  
- **Module Statistiques** : Gère UC5  

---

## **7. Exigences Non-Fonctionnelles**  
- **Sécurité** : Mots de passe hachés (BCrypt), protection contre les injections SQL.  
- **Performance** : Temps de réponse < 2s pour les opérations CRUD.  
- **Portabilité** : Compatible Windows/macOS/Linux (Java Swing).  

---

## **8. Éléments pour Diagrammes**  

### **Pour Diagramme de Classes** :  
- **Classes** : `User`, `Wallet`, `Transaction`, `Crypto`, `*DAO`, `*Controller`  
- **Associations** : Agrégations, dépendances (DAO → Models)  

### **Pour Diagramme de Cas d’Utilisation** :  
- **Acteurs** : Utilisateur, Administrateur  
- **Use Cases** : UC1 à UC5 avec relations « include » (ex: UC3 inclut la validation)  

### **Pour Diagramme de Séquence** :  
- **Interactions** :  
  - `User → AuthController → UserDAO` (Connexion)  
  - `User → WalletController → TransactionDAO → Wallet` (Ajout transaction)  

---

## **9. Validation**  
- **Tests Unitaires** : JUnit pour les DAO/Services.  
- **Scénarios de Test** :  
  - Création utilisateur → Connexion → Ajout portefeuille → Transaction.  

---

Ce document fournit tous les éléments nécessaires pour générer :  
✅ **Diagramme de Classes** (Avec associations et méthodes)  
✅ **Diagramme de Cas d’Utilisation** (Acteurs + flux)  
✅ **Diagramme de Séquence** (Interactions pour les UC critiques)  

**Modifiable** selon les besoins spécifiques du professeur.