package models.animals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AnimalSeeds {
	private ArrayList<Animal> listOfAnimals;
	private static Random random = new Random();

	// Animal Attributes
	private static String[] GENDERS = { "Female", "Male" };
	private static String[] TYPES = { "CAT", "DOG" };
	private static String[] STATUS = { "SOLD", "PENDING", "AVAILABLE" };

	// Animal Names
	private static String[] NAMES = { "Liam", "Noah", "Oliver", "Elijah", "James", "William", "Benjamin", "Lucas",
			"Henry", "Michael", "Mason", "Sebastian", "Ethan", "Logan", "Owen", "Samuel", "Jacob", "Asher", "Aiden",
			"John", "Joseph", "Wyatt", "David", "Leo", "Luke", "Julian", "Hudson", "Grayson", "Matthew", "Ezra",
			"Gabriel", "Carter", "Isaac", "Jayden", "Luca", "Anthony", "Dylan", "Lincoln", "Thomas" };

	// Cat Breeds
	private static final String[] CAT_BREEDS = { "Aegean", "American Shorthair", "American Wirehair", "Bengal",
			"Burmese", "Chantilly Tiffany", "Egyptian Mau", "Elf Cat", "Exotic Shorthair", "Highlander", "Himalayan",
			"Persian", "Peterbald", "Raas", "Ragdoll", "Savannah", "Siberian", "Sphynx", "Turkish Angora",
			"Ukrainian Levkoy" };

	// Dog Breeds
	private static final String[] DOG_BREEDS = { "Afghan Hound", "Bernese Mountain", "Bichon Frise", "Bull Terrier",
			"Cocker Spaniel", "Corgi", "Dalmatian", "Doberman", "French Bulldog", "German Shepherd", "Greyhound",
			"Japanese Chin", "Papillon", "Pomeranian", "Rottweiler", "Saint Bernard", "Shar Pei", "Shiba Inu",
			"Shih Tzu", "Siberian Husky" };

	// Cat Images
	@SuppressWarnings("serial")
	private static final Map<String, String> CAT_IMAGES = new HashMap<String, String>() {
		{
			put("Aegean", "aegean.png");
			put("American Shorthair", "american-shorthair.png");
			put("American Wirehair", "american-wirehair.png");
			put("Bengal", "bengal.png");
			put("Burmese", "burmese.png");
			put("Chantilly Tiffany", "chantilly-tiffany.png");
			put("Egyptian Mau", "egyptian-mau.png");
			put("Elf Cat", "elf-cat.png");
			put("Exotic Shorthair", "exotic-shorthair.png");
			put("Highlander", "highlander.png");
			put("Himalayan", "himalayan.png");
			put("Persian", "persian.png");
			put("Peterbald", "peterbald.png");
			put("Raas", "raas.png");
			put("Ragdoll", "ragdoll.png");
			put("Savannah", "savannah.png");
			put("Siberian", "siberian.png");
			put("Sphynx", "sphynx.png");
			put("Turkish Angora", "turkish-angora.png");
			put("Ukrainian Levkoy", "ukrainian-levkoy.png");
		}
	};

	// Dog Images
	@SuppressWarnings("serial")
	private static final Map<String, String> DOG_IMAGES = new HashMap<String, String>() {
		{
			put("Afghan Hound", "afghan-hound.png");
			put("Bernese Mountain", "bernese-mountain.png");
			put("Bichon Frise", "bichon-frise.png");
			put("Bull Terrier", "bull-terrier.png");
			put("Cocker Spaniel", "cocker-spaniel.png");
			put("Corgi", "corgi.png");
			put("Dalmatian", "dalmatian.png");
			put("Doberman", "doberman.png");
			put("French Bulldog", "french-bulldog.png");
			put("German Shepherd", "german-shepherd.png");
			put("Greyhound", "greyhound.png");
			put("Japanese Chin", "japanese-chin.png");
			put("Papillon", "papillon.png");
			put("Pomeranian", "pomeranian.png");
			put("Rottweiler", "rottweiler.png");
			put("Saint Bernard", "saint-bernard.png");
			put("Shar Pei", "shar-pei.png");
			put("Shiba Inu", "shiba-inu.png");
			put("Shih Tzu", "shih-tzu.png");
			put("Siberian Husky", "siberian-husky.png");
		}
	};

	// Default Constructor Class
	public AnimalSeeds() {
		// Create New List Instances
		this.listOfAnimals = new ArrayList<Animal>();
	}

	// Generate Animals
	public void generateAnimals(int numberOfAnimals) {
		// Generate X Number of Animals
		for (int i = 0; i < numberOfAnimals; i++) {
			// Generate Attributes
			int ID = generateID();
			String type = TYPES[random.nextInt(TYPES.length)];
			double price = generatePrice();
			String name = NAMES[random.nextInt(NAMES.length)];
			String dateOfBirth = generateDOB();
			String gender = GENDERS[random.nextInt(GENDERS.length)];
			String status = STATUS[random.nextInt(STATUS.length)];

			String breed = "";
			String imageURL = "";
			if ("CAT".equalsIgnoreCase(type)) {
				breed = CAT_BREEDS[random.nextInt(CAT_BREEDS.length)];
				imageURL = generateImageURL("CAT", breed);
			} else {
				breed = DOG_BREEDS[random.nextInt(DOG_BREEDS.length)];
				imageURL = generateImageURL("DOG", breed);
			}

			// Create & Initialize Animal Object
			Animal animal = new Animal(ID, type, price, name, dateOfBirth, breed, gender, status, imageURL);
			listOfAnimals.add(animal);
			// System.out.println(animal.toString());
		}
	}

	// Generate Random ID
	public int generateID() {
		int minID = 10000;
		int maxID = 99999;

		return random.nextInt(maxID - minID + 1) + minID;
	}

	// Generate Random Price
	public double generatePrice() {
		int minPrice = 100;
		int maxPrice = 500;
		double price = random.nextInt(maxPrice - minPrice + 1) + minPrice;

		return price;
	}

	// Generate Random DOB
	public String generateDOB() {
		Calendar dob = Calendar.getInstance();
		dob.set(Calendar.YEAR, dob.get(Calendar.YEAR) - random.nextInt(10));
		dob.set(Calendar.MONTH, random.nextInt(12));
		dob.set(Calendar.DAY_OF_MONTH, random.nextInt(dob.getActualMaximum(Calendar.DAY_OF_MONTH)) + 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateOfBirth = format.format(dob.getTime());

		return dateOfBirth;
	}

	// Generate ImageURL Based on Breed
	public String generateImageURL(String type, String breed) {
		if ("CAT".equalsIgnoreCase(type)) {
			return CAT_IMAGES.getOrDefault(breed, "unknown.png");
		} else if ("DOG".equalsIgnoreCase(type)) {
			return DOG_IMAGES.getOrDefault(breed, "unknown.png");
		} else {
			return "unknown.png";
		}
	}

	// Get Animal List
	public ArrayList<Animal> getAnimalList() {
		return listOfAnimals;
	}
}