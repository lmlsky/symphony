/*
 * Symphony - A modern community (forum/BBS/SNS/blog) platform written in Java.
 * Copyright (C) 2012-present, b3log.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.b3log.symphony.util;

import com.vdurmont.emoji.EmojiParser;
import org.b3log.latke.Latkes;

import java.util.regex.Pattern;

/**
 * Emotions utilities.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @author <a href="https://hacpai.com/member/ZephyrJung">Zephyr</a>
 * @author <a href="http://vanessa.b3log.org">Vanessa</a>
 * @version 1.3.0.5, Jun 22, 2019
 * @since 0.2.0
 */
public final class Emotions {

    /**
     * Emoji pattern.
     */
    private static final Pattern EMOJI_PATTERN = Pattern.compile(":.+:");

    /**
     * Determines whether the specified string is a emoji or not.
     *
     * @param string the specified string
     * @return {@code true} if it is a emoji, returns {@code false} otherwise
     */
    public static boolean isEmoji(final String string) {
        for (final String emoji : EMOJIS) {
            if (emoji.equals(string)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Replaces the emoji's alias by its unicode. Example: ":smile:" gives "😄".
     *
     * @param content the specified string to parse
     * @return the string with the mojis replaces by their unicode
     */
    public static String toUnicode(final String content) {
        String ret = EmojiParser.parseToUnicode(content);
        ret = ret.replace("❤", "❤️");
        ret = ret.replace("♥", "♥️");

        return ret;
    }

    /**
     * Replaces the emoji's unicode occurrences by one of their alias (between 2 ':'). Example: "😄" gives ":smile:".
     *
     * @param content the string to parse
     * @return the string with the emojis replaced by their alias.
     */
    public static String toAliases(final String content) {
        return EmojiParser.parseToAliases(content);
    }

    /**
     * Clears the emotions ({@literal [em00], :heart:}) with specified content.
     *
     * @param content the specified content
     * @return cleared content
     */
    public static String clear(final String content) {
        String ret = content.replaceAll("\\[em\\d+]", "");
        for (final String emojiCode : EMOJIS) {
            final String emoji = ":" + emojiCode + ":";
            ret = ret.replace(emoji, "");
        }

        return ret;
    }

    /**
     * Converts the specified content with emotions. Replaces the emoji's alias by its unicode. Example: ":smile:" gives "😄".
     *
     * @param content the specified content
     * @return converted content
     */
    public static String convert(final String content) {
        String ret = content;
        if (!EMOJI_PATTERN.matcher(ret).find()) {
            return ret;
        }

        ret = toUnicode(ret);
        for (final String emojiCode : EMOJIS) {
            String repl = "<img alt=\"" + emojiCode + "\" class=\"emoji\" width=\"18\" src=\""
                    + Latkes.getStaticServePath() + "/emoji/graphics/" + emojiCode;
            final String suffix = "huaji".equals(emojiCode) ? ".gif" : ".png";
            repl += suffix + "\" title=\"" + emojiCode + "\" />";
            ret = ret.replace(":" + emojiCode + ":", repl);
        }

        return ret;
    }

    public static void main(String[] args) {
        String str1 = "Here is a boy: \uD83D\uDC66\uD83C\uDFFF!";
        String str2 = EmojiParser.parseToAliases(str1, EmojiParser.FitzpatrickAction.REMOVE);
    }

    /**
     * Emoji list.
     */
    private static final String[] EMOJIS = {
            "+1",
            "-1",
            "100",
            "1234",
            "8ball",
            "a",
            "ab",
            "abc",
            "abcd",
            "accept",
            "aerial_tramway",
            "airplane",
            "alarm_clock",
            "alien",
            "ambulance",
            "anchor",
            "angel",
            "anger",
            "angry",
            "anguished",
            "ant",
            "apple",
            "aquarius",
            "aries",
            "arrows_clockwise",
            "arrows_counterclockwise",
            "arrow_backward",
            "arrow_double_down",
            "arrow_double_up",
            "arrow_down",
            "arrow_down_small",
            "arrow_forward",
            "arrow_heading_down",
            "arrow_heading_up",
            "arrow_left",
            "arrow_lower_left",
            "arrow_lower_right",
            "arrow_right",
            "arrow_right_hook",
            "arrow_up",
            "arrow_upper_left",
            "arrow_upper_right",
            "arrow_up_down",
            "arrow_up_small",
            "art",
            "articulated_lorry",
            "astonished",
            "atm",
            "b",
            "baby",
            "baby_bottle",
            "baby_chick",
            "baby_symbol",
            "back",
            "baggage_claim",
            "balloon",
            "ballot_box_with_check",
            "bamboo",
            "banana",
            "bangbang",
            "bank",
            "barber",
            "bar_chart",
            "baseball",
            "basketball",
            "bath",
            "bathtub",
            "battery",
            "bear",
            "bee",
            "beer",
            "beers",
            "beetle",
            "beginner",
            "bell",
            "bento",
            "bicyclist",
            "bike",
            "bikini",
            "bird",
            "birthday",
            "black_circle",
            "black_joker",
            "black_large_square",
            "black_medium_small_square",
            "black_medium_square",
            "black_nib",
            "black_small_square",
            "black_square_button",
            "blossom",
            "blowfish",
            "blue_book",
            "blue_car",
            "blue_heart",
            "blush",
            "boar",
            "boat",
            "bomb",
            "book",
            "bookmark",
            "bookmark_tabs",
            "books",
            "boom",
            "boot",
            "bouquet",
            "bow",
            "bowling",
            "boy",
            "bread",
            "bride_with_veil",
            "bridge_at_night",
            "briefcase",
            "broken_heart",
            "bug",
            "bulb",
            "bullettrain_front",
            "bullettrain_side",
            "bus",
            "busstop",
            "busts_in_silhouette",
            "bust_in_silhouette",
            "c",
            "cactus",
            "cake",
            "calendar",
            "calling",
            "camel",
            "camera",
            "cancer",
            "candy",
            "capital_abcd",
            "capricorn",
            "car",
            "card_index",
            "carousel_horse",
            "cat",
            "cat2",
            "cd",
            "chart",
            "chart_with_downwards_trend",
            "chart_with_upwards_trend",
            "checkered_flag",
            "cherries",
            "cherry_blossom",
            "chestnut",
            "chicken",
            "children_crossing",
            "chocolate_bar",
            "christmas_tree",
            "church",
            "cinema",
            "circus_tent",
            "city_sunrise",
            "city_sunset",
            "cl",
            "clap",
            "clapper",
            "clipboard",
            "clock1",
            "clock10",
            "clock1030",
            "clock11",
            "clock1130",
            "clock12",
            "clock1230",
            "clock130",
            "clock2",
            "clock230",
            "clock3",
            "clock330",
            "clock4",
            "clock430",
            "clock5",
            "clock530",
            "clock6",
            "clock630",
            "clock7",
            "clock730",
            "clock8",
            "clock830",
            "clock9",
            "clock930",
            "closed_book",
            "closed_lock_with_key",
            "closed_umbrella",
            "cloud",
            "clubs",
            "cn",
            "cocktail",
            "coffee",
            "cold_sweat",
            "collision",
            "computer",
            "confetti_ball",
            "confounded",
            "confused",
            "congratulations",
            "construction",
            "construction_worker",
            "convenience_store",
            "cookie",
            "cool",
            "cop",
            "copyright",
            "corn",
            "couple",
            "couplekiss",
            "couple_with_heart",
            "cow",
            "cow2",
            "credit_card",
            "crescent_moon",
            "crocodile",
            "crossed_flags",
            "crown",
            "cry",
            "crying_cat_face",
            "crystal_ball",
            "cupid",
            "curly_loop",
            "currency_exchange",
            "curry",
            "custard",
            "customs",
            "cyclone",
            "d",
            "dancer",
            "dancers",
            "dango",
            "dart",
            "dash",
            "date",
            "de",
            "deciduous_tree",
            "department_store",
            "diamonds",
            "diamond_shape_with_a_dot_inside",
            "disappointed",
            "disappointed_relieved",
            "dizzy",
            "dizzy_face",
            "dog",
            "dog2",
            "doge",
            "dollar",
            "dolls",
            "dolphin",
            "door",
            "doughnut",
            "do_not_litter",
            "dragon",
            "dragon_face",
            "dress",
            "dromedary_camel",
            "droplet",
            "dvd",
            "e-mail",
            "e50a",
            "ear",
            "earth_africa",
            "earth_americas",
            "earth_asia",
            "ear_of_rice",
            "egg",
            "eggplant",
            "eight",
            "eight_pointed_black_star",
            "eight_spoked_asterisk",
            "electric_plug",
            "elephant",
            "email",
            "end",
            "envelope",
            "es",
            "euro",
            "european_castle",
            "european_post_office",
            "evergreen_tree",
            "exclamation",
            "expressionless",
            "eyeglasses",
            "eyes",
            "f",
            "facepunch",
            "factory",
            "fallen_leaf",
            "family",
            "fast_forward",
            "fax",
            "fearful",
            "feet",
            "ferris_wheel",
            "file_folder",
            "fire",
            "fireworks",
            "fire_engine",
            "first_quarter_moon",
            "first_quarter_moon_with_face",
            "fish",
            "fishing_pole_and_fish",
            "fish_cake",
            "fist",
            "five",
            "flags",
            "flashlight",
            "floppy_disk",
            "flower_playing_cards",
            "flushed",
            "foggy",
            "football",
            "fork_and_knife",
            "fountain",
            "four",
            "four_leaf_clover",
            "fr",
            "free",
            "fried_shrimp",
            "fries",
            "frog",
            "frowning",
            "fuelpump",
            "full_moon",
            "full_moon_with_face",
            "g",
            "game_die",
            "gb",
            "gem",
            "gemini",
            "ghost",
            "gift",
            "gift_heart",
            "girl",
            "globe_with_meridians",
            "goat",
            "golf",
            "grapes",
            "green_apple",
            "green_book",
            "green_heart",
            "grey_exclamation",
            "grey_question",
            "grimacing",
            "grin",
            "grinning",
            "guardsman",
            "guitar",
            "gun",
            "haircut",
            "hamburger",
            "hammer",
            "hamster",
            "hand",
            "handbag",
            "hankey",
            "hash",
            "hatched_chick",
            "hatching_chick",
            "headphones",
            "heart",
            "heartbeat",
            "heartpulse",
            "hearts",
            "heart_decoration",
            "heart_eyes",
            "heart_eyes_cat",
            "hear_no_evil",
            "heavy_check_mark",
            "heavy_division_sign",
            "heavy_dollar_sign",
            "heavy_exclamation_mark",
            "heavy_minus_sign",
            "heavy_multiplication_x",
            "heavy_plus_sign",
            "helicopter",
            "herb",
            "hibiscus",
            "high_brightness",
            "high_heel",
            "hocho",
            "honeybee",
            "honey_pot",
            "horse",
            "horse_racing",
            "hospital",
            "hotel",
            "hotsprings",
            "hourglass",
            "hourglass_flowing_sand",
            "house",
            "house_with_garden",
            "huaji",
            "hushed",
            "i",
            "icecream",
            "ice_cream",
            "id",
            "ideograph_advantage",
            "imp",
            "inbox_tray",
            "incoming_envelope",
            "information_desk_person",
            "information_source",
            "innocent",
            "interrobang",
            "iphone",
            "it",
            "izakaya_lantern",
            "j",
            "jack_o_lantern",
            "japan",
            "japanese_castle",
            "japanese_goblin",
            "japanese_ogre",
            "jeans",
            "joy",
            "joy_cat",
            "jp",
            "k",
            "key",
            "keycap_ten",
            "kimono",
            "kiss",
            "kissing",
            "kissing_cat",
            "kissing_closed_eyes",
            "kissing_heart",
            "kissing_smiling_eyes",
            "koala",
            "koko",
            "kr",
            "large_blue_circle",
            "large_blue_diamond",
            "large_orange_diamond",
            "last_quarter_moon",
            "last_quarter_moon_with_face",
            "laughing",
            "leaves",
            "ledger",
            "leftwards_arrow_with_hook",
            "left_luggage",
            "left_right_arrow",
            "lemon",
            "leo",
            "leopard",
            "libra",
            "light_rail",
            "link",
            "lips",
            "lipstick",
            "lock",
            "lock_with_ink_pen",
            "lollipop",
            "loop",
            "loudspeaker",
            "love_hotel",
            "love_letter",
            "low_brightness",
            "m",
            "mag",
            "mag_right",
            "mahjong",
            "mailbox",
            "mailbox_closed",
            "mailbox_with_mail",
            "mailbox_with_no_mail",
            "man",
            "mans_shoe",
            "man_with_gua_pi_mao",
            "man_with_turban",
            "maple_leaf",
            "mask",
            "massage",
            "meat_on_bone",
            "mega",
            "melon",
            "memo",
            "mens",
            "metro",
            "microphone",
            "microscope",
            "milky_way",
            "minibus",
            "minidisc",
            "mobile_phone_off",
            "moneybag",
            "money_with_wings",
            "monkey",
            "monkey_face",
            "monorail",
            "mortar_board",
            "mountain_bicyclist",
            "mountain_cableway",
            "mountain_railway",
            "mount_fuji",
            "mouse",
            "mouse2",
            "movie_camera",
            "moyai",
            "muscle",
            "mushroom",
            "musical_keyboard",
            "musical_note",
            "musical_score",
            "mute",
            "nail_care",
            "name_badge",
            "necktie",
            "negative_squared_cross_mark",
            "neutral_face",
            "new",
            "newspaper",
            "new_moon",
            "new_moon_with_face",
            "ng",
            "nine",
            "non-potable_water",
            "nose",
            "notebook",
            "notebook_with_decorative_cover",
            "notes",
            "no_bell",
            "no_bicycles",
            "no_entry",
            "no_entry_sign",
            "no_good",
            "no_mobile_phones",
            "no_mouth",
            "no_pedestrians",
            "no_smoking",
            "nut_and_bolt",
            "o",
            "o2",
            "ocean",
            "octocat",
            "octopus",
            "oden",
            "office",
            "ok",
            "ok_hand",
            "ok_woman",
            "older_man",
            "older_woman",
            "on",
            "oncoming_automobile",
            "oncoming_bus",
            "oncoming_police_car",
            "oncoming_taxi",
            "one",
            "open_file_folder",
            "open_hands",
            "open_mouth",
            "ophiuchus",
            "orange_book",
            "outbox_tray",
            "ox",
            "package",
            "pager",
            "page_facing_up",
            "page_with_curl",
            "palm_tree",
            "panda_face",
            "paperclip",
            "parking",
            "partly_sunny",
            "part_alternation_mark",
            "passport_control",
            "paw_prints",
            "peach",
            "pear",
            "pencil",
            "pencil2",
            "penguin",
            "pensive",
            "performing_arts",
            "persevere",
            "person_frowning",
            "person_with_blond_hair",
            "person_with_pouting_face",
            "phone",
            "pig",
            "pig2",
            "pig_nose",
            "pill",
            "pineapple",
            "pisces",
            "pizza",
            "point_down",
            "point_left",
            "point_right",
            "point_up",
            "point_up_2",
            "police_car",
            "poodle",
            "poop",
            "postal_horn",
            "postbox",
            "potable_water",
            "pouch",
            "poultry_leg",
            "pound",
            "pouting_cat",
            "pray",
            "princess",
            "punch",
            "purple_heart",
            "purse",
            "pushpin",
            "put_litter_in_its_place",
            "question",
            "r",
            "rabbit",
            "rabbit2",
            "racehorse",
            "radio",
            "radio_button",
            "rage",
            "railway_car",
            "rainbow",
            "raised_hand",
            "raised_hands",
            "raising_hand",
            "ram",
            "ramen",
            "rat",
            "recycle",
            "red_car",
            "red_circle",
            "registered",
            "relaxed",
            "relieved",
            "repeat",
            "repeat_one",
            "restroom",
            "revolving_hearts",
            "rewind",
            "ribbon",
            "rice",
            "rice_ball",
            "rice_cracker",
            "rice_scene",
            "ring",
            "rocket",
            "roller_coaster",
            "rooster",
            "rose",
            "rotating_light",
            "round_pushpin",
            "rowboat",
            "ru",
            "rugby_football",
            "running",
            "running_shirt_with_sash",
            "sa",
            "sagittarius",
            "sailboat",
            "sake",
            "sandal",
            "santa",
            "satellite",
            "satisfied",
            "saxophone",
            "school",
            "school_satchel",
            "scissors",
            "scorpius",
            "scream",
            "scream_cat",
            "scroll",
            "seat",
            "secret",
            "seedling",
            "see_no_evil",
            "seven",
            "shaved_ice",
            "sheep",
            "shell",
            "ship",
            "shirt",
            "shoe",
            "shower",
            "signal_strength",
            "six",
            "six_pointed_star",
            "ski",
            "skull",
            "sleeping",
            "sleepy",
            "slot_machine",
            "small_blue_diamond",
            "small_orange_diamond",
            "small_red_triangle",
            "small_red_triangle_down",
            "smile",
            "smiley",
            "smiley_cat",
            "smile_cat",
            "smiling_imp",
            "smirk",
            "smirk_cat",
            "smoking",
            "snail",
            "snake",
            "snowboarder",
            "snowflake",
            "snowman",
            "sob",
            "soccer",
            "soon",
            "sos",
            "sound",
            "space_invader",
            "spades",
            "spaghetti",
            "sparkle",
            "sparkler",
            "sparkles",
            "sparkling_heart",
            "speaker",
            "speak_no_evil",
            "speech_balloon",
            "speedboat",
            "squirrel",
            "star",
            "star2",
            "stars",
            "station",
            "statue_of_liberty",
            "steam_locomotive",
            "stew",
            "straight_ruler",
            "strawberry",
            "stuck_out_tongue",
            "stuck_out_tongue_closed_eyes",
            "stuck_out_tongue_winking_eye",
            "sunflower",
            "sunglasses",
            "sunny",
            "sunrise",
            "sunrise_over_mountains",
            "sun_with_face",
            "surfer",
            "sushi",
            "suspension_railway",
            "sweat",
            "sweat_drops",
            "sweat_smile",
            "sweet_potato",
            "swimmer",
            "symbols",
            "syringe",
            "tada",
            "tanabata_tree",
            "tangerine",
            "taurus",
            "taxi",
            "tea",
            "telephone",
            "telephone_receiver",
            "telescope",
            "tennis",
            "tent",
            "thought_balloon",
            "three",
            "thumbsdown",
            "thumbsup",
            "ticket",
            "tiger",
            "tiger2",
            "tired_face",
            "tm",
            "toilet",
            "tokyo_tower",
            "tomato",
            "tongue",
            "top",
            "tophat",
            "tractor",
            "traffic_light",
            "train",
            "train2",
            "tram",
            "triangular_flag_on_post",
            "triangular_ruler",
            "trident",
            "triumph",
            "trolleybus",
            "trollface",
            "trophy",
            "tropical_drink",
            "tropical_fish",
            "truck",
            "trumpet",
            "tshirt",
            "tulip",
            "turtle",
            "tv",
            "twisted_rightwards_arrows",
            "two",
            "two_hearts",
            "two_men_holding_hands",
            "two_women_holding_hands",
            "u",
            "u5272",
            "u5408",
            "u55b6",
            "u6307",
            "u6708",
            "u6709",
            "u6e80",
            "u7121",
            "u7533",
            "u7981",
            "u7a7a",
            "umbrella",
            "unamused",
            "underage",
            "unicorn_face",
            "unlock",
            "up",
            "us",
            "v",
            "vertical_traffic_light",
            "vhs",
            "vibration_mode",
            "video_camera",
            "video_game",
            "violin",
            "virgo",
            "volcano",
            "vs",
            "walking",
            "waning_crescent_moon",
            "waning_gibbous_moon",
            "warning",
            "watch",
            "watermelon",
            "water_buffalo",
            "wave",
            "wavy_dash",
            "waxing_crescent_moon",
            "waxing_gibbous_moon",
            "wc",
            "weary",
            "wedding",
            "whale",
            "whale2",
            "wheelchair",
            "white_check_mark",
            "white_circle",
            "white_flower",
            "white_large_square",
            "white_medium_small_square",
            "white_medium_square",
            "white_small_square",
            "white_square_button",
            "wind_chime",
            "wine_glass",
            "wink",
            "wolf",
            "woman",
            "womans_clothes",
            "womans_hat",
            "womens",
            "worried",
            "wrench",
            "x",
            "yellow_heart",
            "yen",
            "yum",
            "zap",
            "zero",
            "zzz"
    };

    /**
     * Private constructor.
     */
    private Emotions() {
    }
}
