package com.teb.practice.constants;

import static java.lang.System.in;
import static java.util.List.of;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Constants {

    public static final Scanner SCAN = new Scanner(in);
    public static final String SPACE = " ";

    public static final List<String> WORDS = of(
            "aorta", "axing", "apply", "amend",
            "anyway", "armour", "action", "afraid",
            "anguish", "autopsy", "airlift", "anxiety",
            "adequacy", "assassin", "arsehole", "affluent",

            "bokeh", "brunt", "bento", "blitz",
            "blazer", "banter", "bridge", "broken",
            "blabber", "burglar", "bipolar", "breadth",
            "backache", "blizzard", "beginner", "backward",

            "craze", "cheat", "cello", "coast",
            "chubby", "clique", "census", "cancel",
            "capsize", "concede", "castled", "cheerio",
            "chutzpah", "capsized", "collagen", "chastise",

            "detox", "dummy", "doing", "draft",
            "deluxe", "donkey", "dazzle", "diddle",
            "declare", "dignity", "disease", "drugged",
            "dishonor", "dejected", "daiquiri", "droplets",

            "email", "error", "ebony", "event",
            "evolve", "enrage", "embryo", "excess",
            "expunge", "evident", "embargo", "enzymes",
            "eighteen", "enormous", "estimate", "efficacy",

            "fouls", "fungi", "flush", "fixes",
            "frenzy", "fought", "fleece", "fillet",
            "foxhole", "forward", "flummox", "fitness",
            "fairness", "foxglove", "fishcake", "fatigued",

            "gutsy", "genre", "guide", "graze",
            "gimbal", "grater", "gnarly", "grunge",
            "grapple", "gradual", "garbage", "gherkin",
            "genetics", "genocide", "generous", "garlicky",

            "hello", "hazel", "heard", "husky",
            "hazard", "hotter", "harrow", "hornet",
            "hunting", "highway", "history", "hexagon",
            "hammered", "hitherto", "handcuff", "hurtling",

            "index", "idiot", "icing", "ivory",
            "injury", "iconic", "inhale", "ignore",
            "improve", "instead", "impeach", "inertia",
            "increase", "intimacy", "isotopes", "inquired",

            "joins", "judge", "jeans", "juicy",
            "jitter", "jockey", "jackal", "johnny",
            "juicier", "jacuzzi", "justify", "jackpot",
            "junkyard", "joyously", "jiujitsu", "judicial",

            "kneel", "kayak", "kudos", "kebab",
            "karate", "kindle", "keypad", "knives",
            "kingdom", "kumquat", "knuckle", "kittens",
            "kilogram", "knapsack", "kinetics", "knowable",

            "llama", "linen", "liver", "lucky",
            "luxury", "lounge", "liquid", "lustre",
            "leakage", "lexicon", "ladybug", "lozenge",
            "landline", "lectured", "literacy", "luscious",

            "merge", "mixer", "movie", "medic",
            "memoir", "mucous", "mayhem", "mental",
            "maestro", "mouthed", "meeting", "mailbox",
            "moneybox", "magazine", "mottling", "maintain",

            "noise", "nukes", "nerdy", "naked",
            "nephew", "nuzzle", "novice", "nature",
            "ninepin", "nucleus", "nonstop", "numeric",
            "newfound", "nickname", "nonsense", "narrator",

            "owlet", "outdo", "offer", "oasis",
            "object", "opener", "opaque", "onload",
            "oysters", "offhand", "oversee", "odyssey",
            "oversize", "occasion", "outsmart", "oxymoron",

            "pearl", "pizza", "pinky", "plaza",
            "period", "payoff", "pickup", "plucky",
            "postbox", "patches", "phoenix", "pianist",
            "plankton", "plantain", "puzzling", "poetical",

            "quest", "quilt", "query", "queen",
            "quaker", "quartz", "quotas", "qualms",
            "quality", "quietly", "quarrel", "quantum",
            "quotient", "quadrant", "quirkier", "quantity",

            "razor", "ripen", "rowdy", "rhyme",
            "rodent", "racket", "rhythm", "rugged",
            "recline", "rubbish", "raisins", "relapse",
            "rhizomes", "runnable", "renovate", "roadkill",

            "skimp", "shake", "seize", "smart",
            "safari", "squash", "sedate", "scared",
            "slipper", "surplus", "squishy", "splurge",
            "squirrel", "sailboat", "stunning", "sunlight",

            "toxic", "tapas", "twins", "tacky",
            "tablet", "tissue", "traces", "tuxedo",
            "tactile", "tetanus", "triceps", "tequila",
            "termites", "thankful", "tomahawk", "turnover",

            "under", "usher", "unzip", "upset",
            "upsize", "united", "upmost", "unwrap",
            "unicorn", "unlucky", "ukulele", "unknown",
            "ultimate", "uprising", "username", "unboxing",

            "vying", "vixen", "vinyl", "vouch",
            "vizier", "vacuum", "vortex", "velvet",
            "vertigo", "vintage", "vacancy", "village",
            "vascular", "vultures", "valkyrie", "vacation",

            "water", "wound", "witch", "wrong",
            "winner", "whisky", "weasel", "whimsy",
            "writing", "whacked", "worries", "weekend",
            "wireless", "workbook", "waitress", "wizardry",

            "xerox", "xylem", "xrays", "xenon",

            "yacht", "yummy", "yards", "youth",
            "yellow", "yoghurt", "yanking", "yearbook",

            "zebra", "zonal", "zombie", "zeroes"
    );

    public static final Map<String, Integer> UNITS_MAP =
            ofEntries(
                    entry("one", 1),
                    entry("two", 2),
                    entry("three", 3),
                    entry("four", 4),
                    entry("five", 5),
                    entry("six", 6),
                    entry("seven", 7),
                    entry("eight", 8),
                    entry("nine", 9),
                    entry("ten", 10),
                    entry("eleven", 11),
                    entry("twelve", 12),
                    entry("thirteen", 13),
                    entry("fourteen", 14),
                    entry("fifteen", 15),
                    entry("sixteen", 16),
                    entry("seventeen", 17),
                    entry("eighteen", 18),
                    entry("nineteen", 19));

    public static final Map<String, Integer> TENS_MAP =
            ofEntries(
                    entry("twenty", 20),
                    entry("thirty", 30),
                    entry("forty", 40),
                    entry("fifty", 50),
                    entry("sixty", 60),
                    entry("seventy", 70),
                    entry("eighty", 80),
                    entry("ninety", 90));
}
