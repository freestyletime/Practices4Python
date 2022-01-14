// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getDatabase } from "firebase/database";
import { getAuth, GoogleAuthProvider } from "firebase/auth";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCP2h71adpZUWgM4jfyhGq-mFUAGp51JiY",
  authDomain: "chat-385.firebaseapp.com",
  databaseURL:
    "https://chat-385-default-rtdb.europe-west1.firebasedatabase.app",
  projectId: "chat-385",
  storageBucket: "chat-385.appspot.com",
  messagingSenderId: "67272574065",
  appId: "1:67272574065:web:ac3448b81391691c1b87d0",
  measurementId: "G-KK6H704EKG"
};

// Initialize Firebase and export them so that easily using
export const app = initializeApp(firebaseConfig);
export const analytics = getAnalytics(app);
export const auth = getAuth(app);
export const provider = new GoogleAuthProvider();
provider.setCustomParameters({ prompt: "select_account" });
// Get a reference to the database service
export const database = getDatabase(app);
