# **Cahier des Charges - Projet CryptoManagement**  

## **1. Introduction**  
**Nom du Projet** : CryptoManagement  
**Type d'Application** : Application desktop de gestion de cryptomonnaies  
**Objectif Principal** : Permettre aux utilisateurs de suivre manuellement leurs portefeuilles de cryptomonnaies, d'enregistrer des transactions et de simuler des échanges.  

---

## **2. Fonctionnalités Principales**  

### **2.1 Gestion des Utilisateurs**  
- **Inscription** : Création d'un compte avec email, nom d'utilisateur et mot de passe sécurisé (hachage BCrypt).  
- **Connexion/Déconnexion** : Authentification sécurisée avec gestion de session.  

### **2.2 Gestion des Portefeuilles**  
- **Création/Suppression** : Un utilisateur peut créer plusieurs portefeuilles.  
- **Visualisation** : Affichage des cryptomonnaies détenues et de la valeur totale.  

### **2.3 Gestion des Transactions**  
- **Ajout Manuel** : Saisie des transactions (achat, vente, envoi).  
- **Historique** : Consultation des transactions passées avec filtres (date, type).  

### **2.4 Simulation d'Échanges**  
- **Conversion entre cryptos** : Simulation d'échanges avec calcul automatique des taux.  

### **2.5 Statistiques**  
- **Valeur Totale** : Calcul en temps réel.  
- **Évolution** : Graphiques simples (via JFreeChart).  

---

## **3. Technologies Utilisées**  

### **3.1 Backend**  
- **Langage** : Java 11+  
- **Base de Données** : MySQL (schéma relationnel)  
- **Accès aux Données** : JDBC + DAO Pattern  
- **Sécurité** : Hachage BCrypt  

### **3.2 Frontend**  
- **Interface Graphique** : Java Swing  
- **Bibliothèques Utiles** :  
  - JFreeChart (graphiques)  
  - JCalendar (sélection de dates)  

### **3.3 Outils de Développement**  
- **IDE** : Eclipse/IntelliJ  
- **Gestion de Version** : Git/GitHub  
- **Build** : Maven  

---

## **4. Structure du Projet**  

```
CryptoManagement/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── models/          # Entités (User, Wallet, Transaction...)
│   │   │   ├── dao/             # Accès aux données (interfaces + implémentations)
│   │   │   ├── services/        # Logique métier
│   │   │   ├── controllers/     # Gestion des interactions
│   │   │   ├── views/           # Interfaces Swing
│   │   │   └── utils/           # Utilitaires (hachage, dates...)
│   │   └── resources/
│   │       ├── config/          # Fichiers de configuration
│   │       └── assets/          # Images/icons
│   └── test/                    # Tests unitaires (JUnit 5)
├── sql/                         # Scripts SQL (création BDD)
└── docs/                        # Documentation utilisateur
```

---

## **5. Base de Données (Schéma)**  

### **Tables Principales**  
- **users** : `id, username, email, password_hash`  
- **wallets** : `id, user_id, name, total_value`  
- **cryptocurrencies** : `id, symbol, name, quantity, date_price` (référence statique)  
- **transactions** : `id, wallet_id, crypto_id, type, quantity, price, date`  

---

## **6. Points Forts du Projet**  

✅ **Architecture Modulaire** :  
- Respect du modèle MVC (Modèle-Vue-Contrôleur)  
- Séparation claire des couches (DAO, Services, Contrôleurs)  

✅ **Sécurité** :  
- Mots de passe hachés (BCrypt)  
- Gestion des erreurs SQL/Swing  

✅ **Extensibilité** :  
- Possible d'ajouter des API réelles (CoinGecko, Binance) plus tard  
- Structure adaptée à l'ajout de nouvelles fonctionnalités  

---

## **7. Prérequis & Installation**  

### **7.1 Configuration Requise**  
- Java JDK 11+  
- MySQL 8.0+  
- Maven (pour la gestion des dépendances)  

### **7.2 Étapes d'Installation**  
1. **Cloner le dépôt** :  
   ```bash
   git clone https://github.com/votre-utilisateur/CryptoManagement.git
   ```
2. **Importer dans Eclipse/IntelliJ** comme projet Maven.  
3. **Exécuter les scripts SQL** pour créer la base de données.  
4. **Configurer** `database.properties` avec vos identifiants MySQL.  
5. **Lancer** `App.java` (classe principale).  

---

## **8. Améliorations Futures (Bonus)**  

🔹 **Notifications** : Alertes pour les fluctuations de prix.  
🔹 **Import/Export** : CSV/JSON pour sauvegarder les données.  
🔹 **API Temps Réel** : Récupération automatique des prix via CoinGecko.  

---

## **9. Livrables**  

📁 **Code Source** : Dépôt Git complet  
📄 **Documentation** :  
   - Guide d'utilisation  
   - Javadoc  
📊 **Présentation** : Slides de démonstration (optionnel)  

---

## **10. Conclusion**  
Ce projet permet de maîtriser :  
- **Java avancé** (Swing, JDBC, Design Patterns)  
- **Gestion de bases de données** (MySQL, schéma relationnel)  
- **Bonnes pratiques** (sécurité, architecture modulaire)  

Il servira de base solide pour des évolutions vers une application plus complexe intégrant des données financières en temps réel.  

**Signature** :  
FrancKINANI
