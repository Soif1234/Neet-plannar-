package com.example.data

import com.example.data.model.MistakeEntryEntity
import com.example.data.model.PastPaperTopicEntity
import com.example.data.model.StudyBlockEntity

object InitialData {

    fun getInitialStudyBlocks(): List<StudyBlockEntity> {
        val list = mutableListOf<StudyBlockEntity>()

        fun addDay(
            day: Int,
            week: Int,
            date: String,
            isBuffer: Boolean = false,
            // Morning
            mSubject: String,
            mChapter: String,
            mDesc: String,
            mTarget: Int,
            // Evening 1
            e1Subject: String,
            e1Chapter: String,
            e1Desc: String,
            e1Target: Int,
            // Evening 2
            e2Subject: String,
            e2Chapter: String,
            e2Desc: String,
            e2Target: Int,
            // Night
            nDesc: String
        ) {
            // Morning Block
            list.add(
                StudyBlockEntity(
                    dayNumber = day,
                    weekNumber = week,
                    dateDisplay = "Day $day: $date",
                    blockTitle = "Morning Block (8:00 AM - 9:30 AM)",
                    timeSlot = "8:00 AM - 9:30 AM",
                    blockCategory = "MORNING",
                    subject = mSubject,
                    chapter = mChapter,
                    topicsDescription = mDesc,
                    targetMcqs = mTarget,
                    isBufferDay = isBuffer
                )
            )
            // Evening 1
            list.add(
                StudyBlockEntity(
                    dayNumber = day,
                    weekNumber = week,
                    dateDisplay = "Day $day: $date",
                    blockTitle = "Evening Block 1 (5:00 PM - 7:00 PM)",
                    timeSlot = "5:00 PM - 7:00 PM",
                    blockCategory = "EVENING_1",
                    subject = e1Subject,
                    chapter = e1Chapter,
                    topicsDescription = e1Desc,
                    targetMcqs = e1Target,
                    isBufferDay = isBuffer
                )
            )
            // Evening 2
            list.add(
                StudyBlockEntity(
                    dayNumber = day,
                    weekNumber = week,
                    dateDisplay = "Day $day: $date",
                    blockTitle = "Evening Block 2 (7:00 PM - 9:00 PM)",
                    timeSlot = "7:00 PM - 9:00 PM",
                    blockCategory = "EVENING_2",
                    subject = e2Subject,
                    chapter = e2Chapter,
                    topicsDescription = e2Desc,
                    targetMcqs = e2Target,
                    isBufferDay = isBuffer
                )
            )
            // Night Audit
            list.add(
                StudyBlockEntity(
                    dayNumber = day,
                    weekNumber = week,
                    dateDisplay = "Day $day: $date",
                    blockTitle = "Night Block (9:30 PM - 11:00 PM)",
                    timeSlot = "9:30 PM - 11:00 PM",
                    blockCategory = "NIGHT_AUDIT",
                    subject = "Audit / Consolidation",
                    chapter = "Mistake Notebook & Audit",
                    topicsDescription = nDesc,
                    targetMcqs = 0,
                    isBufferDay = isBuffer
                )
            )
        }

        // --- WEEK 1 (Sept 12 - Sept 18) ---
        addDay(
            1, 1, "Saturday, 12/9/26", false,
            "Biology", "Cell Structure and Function (Part 1)", "Read NCERT for Prokaryotic vs. Eukaryotic cells and Cell Membrane. Do 40 MCQs.", 40,
            "Chemistry", "Some Basic Concepts of Chemistry (Part 1)", "Focus on Mole Concept and Molar Mass. Practice 50 MCQs.", 50,
            "Physics", "Units and Measurements (Part 1)", "Focus on Dimensions and Dimensional Analysis. Practice 45 MCQs.", 45,
            "Log today's calculation and memory errors in your Mistake Notebook. Categorize errors as Calculation, Reading, Memory, or Conceptual."
        )
        addDay(
            2, 1, "Sunday, 13/9/26", false,
            "Biology", "Cell Structure and Function (Part 2)", "Read NCERT for Endomembrane system, Mitochondria, Chloroplast. Do 40 MCQs.", 40,
            "Chemistry", "Some Basic Concepts of Chemistry (Part 2)", "Focus on Stoichiometry and Limiting Reagents. Practice 40-50 MCQs.", 45,
            "Physics", "Units and Measurements (Part 2)", "Focus on Error Analysis and Measuring Instruments. Practice 45 MCQs.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            3, 1, "Monday, 14/9/26", false,
            "Biology", "Cell Structure and Function (Part 3)", "Read NCERT for Nucleus and Chromosomes. Do a 50-MCQ wrap-up test.", 50,
            "Chemistry", "Some Basic Concepts of Chemistry (Part 3)", "Focus on Concentration terms. Do a 40-MCQ chapter wrap-up.", 40,
            "Physics", "Motion in a Straight Line (Part 1)", "Focus on Distance, Displacement, Average Velocity/Speed. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            4, 1, "Tuesday, 15/9/26", false,
            "Biology", "Cell Cycle and Cell Division (Part 1)", "Read NCERT for Cell cycle phases and Mitosis. Practice 40 MCQs.", 40,
            "Chemistry", "Structure of Atom (Part 1)", "Focus on the Bohr Model and Hydrogen Spectrum. Practice 40 MCQs.", 40,
            "Physics", "Motion in a Straight Line (Part 2)", "Focus on Kinematic Equations and Free Fall. Practice 50 numericals.", 50,
            "Update Mistake Notebook."
        )
        addDay(
            5, 1, "Wednesday, 16/9/26", false,
            "Biology", "Cell Cycle and Cell Division (Part 2)", "Read NCERT for Meiosis I, Meiosis II. Do 50 MCQs chapter wrap-up.", 50,
            "Chemistry", "Structure of Atom (Part 2)", "Focus on Quantum Mechanical Model, De Broglie, Heisenberg. Practice 45 MCQs.", 45,
            "Physics", "Motion in a Straight Line (Part 3)", "Focus on Relative Velocity in 1D and Kinematic Graphs. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            6, 1, "Thursday, 17/9/26", false,
            "Biology", "Biomolecules (Part 1)", "Read NCERT for Amino acids, Proteins, and Primary/Secondary structures. Practice 40 MCQs.", 40,
            "Chemistry", "Structure of Atom (Part 3)", "Focus on Electronic Configuration rules. Do a 50-MCQ chapter wrap-up.", 50,
            "Physics", "Motion in a Plane (Part 1)", "Focus on Vector addition, subtraction, and resolution. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            7, 1, "Friday, 18/9/26 (Buffer & Consolidation Day)", true,
            "Biology", "Cell Biology & Biomolecules Review", "Review Mistake Notebook for Cell Structure, Cell Cycle, and Biomolecules.", 0,
            "Chemistry", "Mole Concept & Atomic Structure Test", "Mixed 50-MCQ timed test (Mole Concept + Atomic Structure).", 50,
            "Physics", "Units & 1D Motion Test", "Mixed 50-MCQ timed test (Units & Dimensions + 1D Motion).", 50,
            "Final weekly audit. Consolidate error logs and verify progress."
        )

        // --- WEEK 2 (Sept 19 - Sept 25) ---
        addDay(
            8, 2, "Saturday, 19/9/26", false,
            "Biology", "Biomolecules (Part 2)", "Read NCERT for Lipids and Nucleic Acids. Practice 40 MCQs.", 40,
            "Chemistry", "Classification of Elements and Periodicity (Part 1)", "Focus on Atomic Radius and Ionization Enthalpy trends. Practice 50 MCQs.", 50,
            "Physics", "Motion in a Plane (Part 2)", "Focus on Projectile Motion. Practice 45 numericals.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            9, 2, "Sunday, 20/9/26", false,
            "Biology", "Biomolecules (Part 3)", "Read NCERT for Enzymes. Do a 50-MCQ chapter wrap-up.", 50,
            "Chemistry", "Classification of Elements (Part 2)", "Focus on Electron Gain Enthalpy and Electronegativity. Do a 40-MCQ chapter wrap-up.", 40,
            "Physics", "Motion in a Plane (Part 3)", "Focus on Uniform Circular Motion. Do a 45-MCQ chapter wrap-up.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            10, 2, "Monday, 21/9/26", false,
            "Biology", "Mega-Revision (Unit 3)", "Take a combined 90-MCQ timed test for Cell Structure, Cell Cycle, and Biomolecules.", 90,
            "Chemistry", "Chemical Bonding (Part 1)", "Focus on Ionic/Covalent bonds, Lewis dot structures, and Fajan's Rule. Practice 40 MCQs.", 40,
            "Physics", "Laws of Motion (Part 1)", "Focus on Newton's Laws and Conservation of Momentum. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            11, 2, "Tuesday, 22/9/26", false,
            "Biology", "Structural Organization in Animals (Part 1)", "Focus heavily on Frog anatomy and Animal Tissues. Practice 40 MCQs.", 40,
            "Chemistry", "Chemical Bonding (Part 2)", "Focus strictly on VSEPR Theory and Hybridization. Practice 50 MCQs.", 50,
            "Physics", "Laws of Motion (Part 2)", "Focus on Friction (Static and Kinetic). Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            12, 2, "Wednesday, 23/9/26", false,
            "Biology", "Structural Organization in Animals (Part 2)", "Do a 50-MCQ chapter wrap-up.", 50,
            "Chemistry", "Chemical Bonding (Part 3)", "Focus on Molecular Orbital Theory (MOT) and Hydrogen Bonding. Do a 50-MCQ chapter wrap-up.", 50,
            "Physics", "Laws of Motion (Part 3)", "Focus on Dynamics of Circular Motion. Do a 50-MCQ chapter wrap-up.", 50,
            "Update Mistake Notebook."
        )
        addDay(
            13, 2, "Thursday, 24/9/26", false,
            "Biology", "Morphology of Flowering Plants (Part 1)", "Read NCERT for Root, Stem, and Leaf modifications. Practice 40 MCQs.", 40,
            "Chemistry", "Chemical Bonding (Advanced Practice)", "Spend an extra 2 hours doing 60-70 PYQs.", 65,
            "Physics", "Work, Energy, and Power (Part 1)", "Focus on Work done by constant/variable forces and the Work-Energy Theorem. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            14, 2, "Friday, 25/9/26 (Buffer & Consolidation Day)", true,
            "Biology", "Weeks 1 & 2 Review", "Review Mistake Notebook for Weeks 1 and 2.", 0,
            "Chemistry", "Periodic Table + Bonding Test", "Mixed 50-MCQ timed test (Periodic Table + Chemical Bonding).", 50,
            "Physics", "2D Motion + Laws of Motion Test", "Mixed 50-MCQ timed test (Motion in a Plane + Laws of Motion).", 50,
            "Final weekly audit."
        )

        // --- WEEK 3 (Sept 26 - Oct 2) ---
        addDay(
            15, 3, "Saturday, 26/9/26", false,
            "Biology", "Morphology of Flowering Plants (Part 2)", "Focus on Inflorescence, Flower parts, Fruit, and Seed. Practice 40 MCQs.", 40,
            "Chemistry", "Solutions (Part 1)", "Focus on Types of Solutions, Solubility, and Raoult's Law. Practice 50 MCQs.", 50,
            "Physics", "Work, Energy, and Power (Part 2)", "Focus on Potential Energy, Conservation of Mechanical Energy, and Power. Practice 45 numericals.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            16, 3, "Sunday, 27/9/26", false,
            "Biology", "Morphology of Flowering Plants (Part 3)", "Memorize the Plant Families. Do a 50-MCQ chapter wrap-up.", 50,
            "Chemistry", "Solutions (Part 2)", "Focus strictly on Colligative Properties. Practice 40 MCQs.", 40,
            "Physics", "Work, Energy, and Power (Part 3)", "Focus on Elastic and Inelastic Collisions in 1D and 2D. Do a 45-MCQ chapter wrap-up test.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            17, 3, "Monday, 28/9/26", false,
            "Biology", "Photosynthesis in Higher Plants (Part 1)", "Read NCERT for Early Experiments, Site of Photosynthesis, and Light Reaction. Practice 40 MCQs.", 40,
            "Chemistry", "Solutions (Part 3)", "Focus on Abnormal Molar Mass and Van't Hoff Factor. Do a 45-MCQ chapter wrap-up.", 45,
            "Physics", "System of Particles and Rotational Motion (Part 1)", "Focus on Center of Mass. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            18, 3, "Tuesday, 29/9/26", false,
            "Biology", "Photosynthesis in Higher Plants (Part 2)", "Focus on the Dark Reaction and C4 Pathway. Practice 40 MCQs.", 40,
            "Chemistry", "Electrochemistry (Part 1)", "Focus on Galvanic Cells, Electrode Potential, and the Nernst Equation. Practice 45 MCQs.", 45,
            "Physics", "Rotational Motion (Part 2)", "Focus on Vector Product, Torque, and Angular Momentum. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            19, 3, "Wednesday, 30/9/26", false,
            "Biology", "Photosynthesis in Higher Plants (Part 3)", "Focus on Photorespiration and Factors affecting Photosynthesis. Do a 50-MCQ chapter wrap-up.", 50,
            "Chemistry", "Electrochemistry (Part 2)", "Focus on Conductance in electrolytic solutions and Kohlrausch's Law. Practice 40 MCQs.", 40,
            "Physics", "Rotational Motion (Part 3)", "Focus heavily on Moment of Inertia. Practice 50 numericals.", 50,
            "Update Mistake Notebook."
        )
        addDay(
            20, 3, "Thursday, 1/10/26", false,
            "Biology", "Respiration in Plants (Part 1)", "Read NCERT for Glycolysis and Fermentation. Practice 40 MCQs.", 40,
            "Chemistry", "Electrochemistry (Part 3)", "Focus on Electrolytic cells, Faraday's Laws, and Batteries. Do a 50-MCQ chapter wrap-up.", 50,
            "Physics", "Rotational Motion (Part 4)", "Focus on Kinematics of Rotational Motion and Rolling Motion. Do a 50-MCQ chapter wrap-up test.", 50,
            "Update Mistake Notebook."
        )
        addDay(
            21, 3, "Friday, 2/10/26 (Buffer & Consolidation Day)", true,
            "Biology", "Morphology & Photosynthesis Review", "Review Mistake Notebook for Morphology and Photosynthesis.", 0,
            "Chemistry", "Solutions + Electrochemistry Test", "Mixed 50-MCQ timed test (Solutions + Electrochemistry).", 50,
            "Physics", "Work Power & Rotational Motion Test", "Mixed 50-MCQ timed test (Work, Energy & Power + Rotational Motion).", 50,
            "Final weekly audit."
        )

        // --- WEEK 4 (Oct 3 - Oct 9) ---
        addDay(
            22, 4, "Saturday, 3/10/26", false,
            "Biology", "Respiration in Plants (Part 2)", "Focus on the Krebs Cycle and the Electron Transport System (ETS). Practice 40 MCQs.", 40,
            "Chemistry", "Chemical Kinetics (Part 1)", "Focus on Rate of Reaction, factors affecting rate, and Rate Law. Practice 50 MCQs.", 50,
            "Physics", "Gravitation (Part 1)", "Focus on Kepler's Laws, Universal Law of Gravitation, and Acceleration due to gravity. Practice 45 numericals.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            23, 4, "Sunday, 4/10/26", false,
            "Biology", "Respiration in Plants (Part 3)", "Focus on the Amphibolic pathway and Respiratory Quotient. Do a 45-MCQ chapter wrap-up.", 45,
            "Chemistry", "Chemical Kinetics (Part 2)", "Focus strictly on Order and Molecularity, and Integrated Rate Equations. Practice 40 MCQs.", 40,
            "Physics", "Gravitation (Part 2)", "Focus on Gravitational Potential Energy, Gravitational Potential, and Escape Velocity. Practice 45 MCQs.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            24, 4, "Monday, 5/10/26", false,
            "Biology", "Plant Growth and Development (Part 1)", "Read NCERT for Growth rates, phases of growth, and differentiation. Practice 40 MCQs.", 40,
            "Chemistry", "Chemical Kinetics (Part 3)", "Focus on Half-life, Pseudo first-order reactions, and the Arrhenius Equation. Do a 50-MCQ chapter wrap-up.", 50,
            "Physics", "Gravitation (Part 3)", "Focus on Earth Satellites, Orbital Velocity, and Time Period. Do a 50-MCQ chapter wrap-up.", 50,
            "Update Mistake Notebook."
        )
        addDay(
            25, 4, "Tuesday, 6/10/26", false,
            "Biology", "Plant Growth and Development (Part 2)", "Focus heavily on Plant Growth Regulators (Auxins, Gibberellins, Cytokinins). Practice 45 MCQs.", 45,
            "Chemistry", "Redox Reactions (Part 1)", "Focus on Oxidation number calculations and balancing redox reactions. Practice 40 MCQs.", 40,
            "Physics", "Mechanical Properties of Solids (Part 1)", "Focus on Elastic behavior, Stress, Strain, Hooke's Law, and Young's Modulus. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            26, 4, "Wednesday, 7/10/26", false,
            "Biology", "Plant Growth and Development (Part 3)", "Focus on Ethylene and Abscisic acid. Do a 45-MCQ chapter wrap-up.", 45,
            "Chemistry", "Redox Reactions (Part 2)", "Focus on types of redox reactions and standard electrode potential. Do a 45-MCQ chapter wrap-up.", 45,
            "Physics", "Mechanical Properties of Solids (Part 2)", "Focus on Shear Modulus, Bulk Modulus, and Elastic Potential Energy. Do a 45-MCQ chapter wrap-up.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            27, 4, "Thursday, 8/10/26", false,
            "Biology", "Mega-Revision (Plant Physiology Unit)", "Take a combined 90-MCQ timed test to lock in this high-yield unit.", 90,
            "Chemistry", "Advanced Physical Chemistry Practice", "Spend 2 hours doing 60-70 PYQs mixing Chemical Kinetics and Electrochemistry.", 65,
            "Physics", "Mechanical Properties of Fluids (Part 1)", "Focus on Pressure, Pascal's Law, and Archimedes' Principle. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            28, 4, "Friday, 9/10/26 (Buffer & Consolidation Day)", true,
            "Biology", "Plant Physiology Unit Review", "Review Mistake Notebook for the Plant Physiology unit.", 0,
            "Chemistry", "Kinetics + Redox Test", "Mixed 50-MCQ timed test (Chemical Kinetics + Redox Reactions).", 50,
            "Physics", "Gravitation + Solids Test", "Mixed 50-MCQ timed test (Gravitation + Solids).", 50,
            "Final weekly audit. Evaluate stamina as you finish month 1."
        )

        // --- WEEK 5 (Oct 10 - Oct 16) ---
        addDay(
            29, 5, "Saturday, 10/10/26", false,
            "Biology", "Sexual Reproduction in Flowering Plants (Part 1)", "Focus on Flower structure, Stamen, Microsporangium, and Pollen grain. Practice 40 MCQs.", 40,
            "Chemistry", "General Organic Chemistry (GOC) (Part 1)", "Focus on IUPAC Nomenclature of organic compounds. Practice 50 MCQs.", 50,
            "Physics", "Mechanical Properties of Fluids (Part 2)", "Focus on Streamline flow, Equation of Continuity, and Bernoulli's Principle. Practice 45 numericals.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            30, 5, "Sunday, 11/10/26", false,
            "Biology", "Sexual Reproduction in Flowering Plants (Part 2)", "Focus on Pistil, Megasporangium (Ovule), and Pollination types/agencies. Practice 45 MCQs.", 45,
            "Chemistry", "General Organic Chemistry (Part 2)", "Focus strictly on Isomerism (Structural and introductory Stereoisomerism). Practice 45 MCQs.", 45,
            "Physics", "Mechanical Properties of Fluids (Part 3)", "Focus on Viscosity, Stokes' Law, Terminal Velocity, and Surface Tension. Do a 45-MCQ chapter wrap-up.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            31, 5, "Monday, 12/10/26", false,
            "Biology", "Sexual Reproduction in Flowering Plants (Part 3)", "Focus on Double Fertilization, Endosperm, and Embryo development. Do a 45-MCQ chapter wrap-up.", 45,
            "Chemistry", "General Organic Chemistry (Part 3)", "Focus heavily on Electronic effects: Inductive Effect, Electromeric Effect, and Resonance. Practice 40 MCQs.", 40,
            "Physics", "Thermal Properties of Matter (Part 1)", "Focus on Temperature scales, Heat, and Thermal Expansion. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            32, 5, "Tuesday, 13/10/26", false,
            "Biology", "Human Reproduction (Part 1)", "Read NCERT for Male and Female reproductive systems. Practice 40 MCQs.", 40,
            "Chemistry", "General Organic Chemistry (Part 4)", "Focus on Hyperconjugation and the Stability of reaction intermediates. Practice 45 MCQs.", 45,
            "Physics", "Thermal Properties of Matter (Part 2)", "Focus strictly on Specific Heat Capacity and Calorimetry. Practice 45 MCQs.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            33, 5, "Wednesday, 14/10/26", false,
            "Biology", "Human Reproduction (Part 2)", "Focus on Gametogenesis and the Menstrual Cycle. Practice 45 MCQs.", 45,
            "Chemistry", "General Organic Chemistry (Part 5)", "Focus on fundamental concepts of organic reaction mechanisms (bond fission, electrophiles, nucleophiles). Practice 40 MCQs.", 40,
            "Physics", "Thermal Properties of Matter (Part 3)", "Focus on Heat Transfer and Newton's Law of Cooling. Do a 45-MCQ chapter wrap-up.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            34, 5, "Thursday, 15/10/26", false,
            "Biology", "Human Reproduction (Part 3)", "Focus on Fertilization, Implantation, Pregnancy, and Parturition. Do a 45-MCQ chapter wrap-up.", 45,
            "Chemistry", "General Organic Chemistry (Part 6)", "Focus on Methods of purification and qualitative analysis of organic compounds. Do a 50-MCQ chapter wrap-up.", 50,
            "Physics", "Fluids & Thermal Properties Advanced Practice", "Spend 2 hours doing 60-70 PYQs mixing Fluid Mechanics and Thermal Properties.", 65,
            "Update Mistake Notebook."
        )
        addDay(
            35, 5, "Friday, 16/10/26 (Buffer & Consolidation Day)", true,
            "Biology", "Reproduction Unit Review", "Review Mistake Notebook for Sexual Reproduction in Flowering Plants and Human Reproduction.", 0,
            "Chemistry", "General Organic Chemistry Mixed Test", "Mixed 50-MCQ timed test covering all of General Organic Chemistry.", 50,
            "Physics", "Fluid Mechanics + Thermal Properties Test", "Mixed 50-MCQ timed test (Fluid Mechanics + Thermal Properties of Matter).", 50,
            "Final weekly audit."
        )

        // --- WEEK 6 (Oct 17 - Oct 23) ---
        addDay(
            36, 6, "Saturday, 17/10/26", false,
            "Biology", "Reproductive Health (Part 1)", "Read NCERT for Population Stabilization, Birth Control, and Contraceptives. Practice 40 MCQs.", 40,
            "Chemistry", "Hydrocarbons (Part 1)", "Focus on Alkanes (preparation and properties). Practice 50 MCQs.", 50,
            "Physics", "Thermodynamics (Part 1)", "Focus on Thermal Equilibrium, Zeroth Law, Heat, Internal Energy, and First Law. Practice 45 numericals.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            37, 6, "Sunday, 18/10/26", false,
            "Biology", "Reproductive Health (Part 2)", "Focus on MTP, STDs, and Infertility (ART, IVF, ZIFT). Do a 45-MCQ chapter wrap-up.", 45,
            "Chemistry", "Hydrocarbons (Part 2)", "Focus strictly on Alkenes (preparation, Markovnikov's rule, ozonolysis). Practice 45 MCQs.", 45,
            "Physics", "Thermodynamics (Part 2)", "Focus on Thermodynamic Processes (Isothermal, Adiabatic, Isochoric, Isobaric) and work done. Practice 45 MCQs.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            38, 6, "Monday, 19/10/26", false,
            "Biology", "Mega-Revision (Reproduction Unit)", "Take a combined 90-MCQ timed test to lock in this high-yield unit.", 90,
            "Chemistry", "Hydrocarbons (Part 3)", "Focus on Alkynes (preparation and chemical reactions). Practice 40 MCQs.", 40,
            "Physics", "Thermodynamics (Part 3)", "Focus on Heat Engines, Refrigerators, Second Law, and Carnot Engine. Do a 45-MCQ chapter wrap-up.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            39, 6, "Tuesday, 20/10/26", false,
            "Biology", "Biological Classification (Part 1)", "Read NCERT for Monera and Protista. Practice 45 MCQs.", 45,
            "Chemistry", "Hydrocarbons (Part 4)", "Focus on Aromatic Hydrocarbons (Benzene structure, resonance, electrophilic substitution). Practice 45 MCQs.", 45,
            "Physics", "Kinetic Theory of Gases (Part 1)", "Focus on Equation of State of a Perfect Gas, and Kinetic Theory postulates. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            40, 6, "Wednesday, 21/10/26", false,
            "Biology", "Biological Classification (Part 2)", "Focus on Fungi, Viruses, Viroids, and Prions. Do a 50-MCQ chapter wrap-up.", 50,
            "Chemistry", "Hydrocarbons (Part 5)", "Do a 60-MCQ chapter wrap-up focusing heavily on conversion reactions.", 60,
            "Physics", "Kinetic Theory of Gases (Part 2)", "Focus on Degrees of Freedom, Law of Equipartition of Energy, and Specific Heat capacity. Do a 45-MCQ chapter wrap-up.", 45,
            "Update Mistake Notebook."
        )
        addDay(
            41, 6, "Thursday, 22/10/26", false,
            "Biology", "Plant Kingdom (Quick Review)", "Focus strictly on Algae and Bryophytes. Practice 45 MCQs.", 45,
            "Chemistry", "Thermodynamics - Chemistry (Part 1)", "Focus on System types, Internal Energy, Work, and Heat. Practice 40 MCQs.", 40,
            "Physics", "Oscillations (Part 1)", "Focus on Periodic motion, Simple Harmonic Motion (SHM), and the equations for displacement/velocity. Practice 40 MCQs.", 40,
            "Update Mistake Notebook."
        )
        addDay(
            42, 6, "Friday, 23/10/26 (Buffer & Consolidation Day)", true,
            "Biology", "Reproductive Health & Biological Classification Review", "Review Mistake Notebook for Reproductive Health and Biological Classification.", 0,
            "Chemistry", "Hydrocarbons Mixed Test", "Mixed 50-MCQ timed test covering all of Hydrocarbons.", 50,
            "Physics", "Thermodynamics + Kinetic Theory Test", "Mixed 50-MCQ timed test (Thermodynamics + Kinetic Theory of Gases).", 50,
            "Final weekly audit. Complete 6-week recovery phase consolidation!"
        )

        return list
    }

    fun getInitialMistakes(): List<MistakeEntryEntity> {
        return listOf(
            MistakeEntryEntity(
                subject = "Physics",
                chapter = "Units and Measurements",
                questionSummary = "Dimension of Magnetic Flux [Φ]: calculation in numerator vs denominator.",
                errorCategory = "Silly/Calculation Error",
                whyReason = "Minor math slip-up while inverting exponents in Φ = B·A. Added 2+3=6 instead of 5.",
                correctTakeaway = "[Φ] = [B][A] = [M L² T⁻² A⁻¹]. Double check arithmetic powers before finalizing.",
                isReviewed = true
            ),
            MistakeEntryEntity(
                subject = "Biology",
                chapter = "Cell Structure and Function",
                questionSummary = "Which of the following organelles is NOT part of the endomembrane system?",
                errorCategory = "Reading Error",
                whyReason = "Rushed through the question stem and skipped over the negative keyword 'NOT'. Selected Golgi apparatus instinctively.",
                correctTakeaway = "Endomembrane system coordinates: ER, Golgi complex, Lysosomes, and Vacuoles. Peroxisomes, Chloroplasts & Mitochondria are NOT part of it.",
                isReviewed = true
            ),
            MistakeEntryEntity(
                subject = "Chemistry - Inorganic",
                chapter = "Classification of Elements",
                questionSummary = "Order of negative Electron Gain Enthalpy among Halogens (Cl vs F).",
                errorCategory = "Memory Error",
                whyReason = "Forgot the famous exception that Chlorine has more negative electron gain enthalpy than Fluorine due to 2p interelectronic repulsion.",
                correctTakeaway = "Order of Δ_egH: Cl > F > Br > I. Fluorine's extremely small 2p orbital creates strong interelectronic repulsions for incoming electron.",
                isReviewed = false
            ),
            MistakeEntryEntity(
                subject = "Physics",
                chapter = "Motion in a Straight Line",
                questionSummary = "Distance travelled in the 5th second for a particle under constant retarding acceleration.",
                errorCategory = "Conceptual Error",
                whyReason = "Blindly applied S_n = u + a/2(2n - 1) without checking if the particle came to rest and reversed direction during that 5th second.",
                correctTakeaway = "Always compute stopping time t_stop = u/|a| first. If t_stop falls inside the nth second, split calculation into forward and reverse distance.",
                isReviewed = false
            ),
            MistakeEntryEntity(
                subject = "Chemistry - Physical",
                chapter = "Thermodynamics",
                questionSummary = "Comparison of work done during adiabatic expansion for reversible vs irreversible paths.",
                errorCategory = "Conceptual Error",
                whyReason = "Assumed work magnitude is greater in irreversible expansion because final temperature is higher, overlooking that reversible path does maximum work.",
                correctTakeaway = "In adiabatic expansion: |W_rev| > |W_irrev|. Reversible adiabatic expansion cools the gas more (T2_rev < T2_irrev).",
                isReviewed = false
            ),
            MistakeEntryEntity(
                subject = "Biology",
                chapter = "Genetics & Molecular Basis of Inheritance",
                questionSummary = "Which molecule acts as the natural inducer of the lac operon?",
                errorCategory = "Memory Error",
                whyReason = "Confused Lactose with Allolactose in the active repressor binding mechanism.",
                correctTakeaway = "Allolactose (isomer formed by β-galactosidase) is the true physiological inducer that binds and inactivates the repressor protein.",
                isReviewed = true
            ),
            MistakeEntryEntity(
                subject = "Physics",
                chapter = "System of Particles & Rotational Motion",
                questionSummary = "Fraction of total kinetic energy associated with rotational motion for a solid sphere.",
                errorCategory = "Silly/Calculation Error",
                whyReason = "Used I = 1/2 MR² (disc) instead of I = 2/5 MR² (solid sphere) in the K_rot / K_total calculation.",
                correctTakeaway = "Solid sphere: K_rot/K_total = (2/5) / (1 + 2/5) = 2/7 (~28.6%). Solid cylinder/disc is 1/3 (33.3%). Ring is 1/2 (50%).",
                isReviewed = false
            ),
            MistakeEntryEntity(
                subject = "Chemistry - Organic",
                chapter = "General Organic Chemistry (GOC)",
                questionSummary = "Identifying the most stable carbocation among substituted allylic vs tertiary benzylic species.",
                errorCategory = "Reading Error",
                whyReason = "Misidentified the position of the double bond on the ring, reading it as non-conjugated when it was conjugated.",
                correctTakeaway = "Aromatic resonance > Extended conjugated allylic > 3° Hyperconjugation (9 α-H) > 2° > 1°. Carefully trace double bonds on paper.",
                isReviewed = true
            )
        )
    }

    fun getInitialPastPaperTopics(): List<PastPaperTopicEntity> {
        return listOf(
            PastPaperTopicEntity(
                subject = "Biology",
                chapter = "Genetics & Molecular Basis of Inheritance",
                avgQuestionsPerYear = 12.5f,
                weightagePercentage = 13.8f,
                weightageTier = "Critical High-Yield",
                userAccuracyPercent = 78,
                totalMistakesLogged = 2,
                recommendedActions = "Revise Pedigree analysis, Lac Operon, and DNA replication enzymes."
            ),
            PastPaperTopicEntity(
                subject = "Biology",
                chapter = "Cell Biology & Biomolecules",
                avgQuestionsPerYear = 9.0f,
                weightagePercentage = 10.0f,
                weightageTier = "Critical High-Yield",
                userAccuracyPercent = 85,
                totalMistakesLogged = 1,
                recommendedActions = "Revisit Cell division Meiosis stages and Protein structures (Week 1 topics)."
            ),
            PastPaperTopicEntity(
                subject = "Biology",
                chapter = "Plant Physiology (Photosynthesis & Respiration)",
                avgQuestionsPerYear = 8.0f,
                weightagePercentage = 8.8f,
                weightageTier = "High Yield",
                userAccuracyPercent = 64,
                totalMistakesLogged = 4,
                recommendedActions = "High error alert! Re-read NCERT ETS complex sequence and C4 Krantz anatomy."
            ),
            PastPaperTopicEntity(
                subject = "Chemistry",
                chapter = "Chemical Bonding & Molecular Structure",
                avgQuestionsPerYear = 4.5f,
                weightagePercentage = 5.0f,
                weightageTier = "Critical High-Yield",
                userAccuracyPercent = 58,
                totalMistakesLogged = 5,
                recommendedActions = "High weightage with gap! Drill 60 PYQs on MOT bond order and VSEPR shapes."
            ),
            PastPaperTopicEntity(
                subject = "Chemistry",
                chapter = "General Organic Chemistry (GOC)",
                avgQuestionsPerYear = 5.0f,
                weightagePercentage = 5.5f,
                weightageTier = "Critical High-Yield",
                userAccuracyPercent = 62,
                totalMistakesLogged = 4,
                recommendedActions = "Focus on Carbocation stability, Hyperconjugation vs Resonance priority."
            ),
            PastPaperTopicEntity(
                subject = "Chemistry",
                chapter = "Solutions & Electrochemistry",
                avgQuestionsPerYear = 4.0f,
                weightagePercentage = 4.4f,
                weightageTier = "High Yield",
                userAccuracyPercent = 72,
                totalMistakesLogged = 2,
                recommendedActions = "Practice Nernst equation concentration cells & Van't Hoff i factor."
            ),
            PastPaperTopicEntity(
                subject = "Physics",
                chapter = "System of Particles & Rotational Motion",
                avgQuestionsPerYear = 3.5f,
                weightagePercentage = 3.8f,
                weightageTier = "High Yield",
                userAccuracyPercent = 52,
                totalMistakesLogged = 6,
                recommendedActions = "Critical gap! Watch 1.5x video on Rolling without slipping + Moment of Inertia theorems."
            ),
            PastPaperTopicEntity(
                subject = "Physics",
                chapter = "Thermodynamics & Kinetic Theory",
                avgQuestionsPerYear = 4.0f,
                weightagePercentage = 4.4f,
                weightageTier = "High Yield",
                userAccuracyPercent = 68,
                totalMistakesLogged = 3,
                recommendedActions = "Master sign conventions for work done: W = PΔV and Carnot engine efficiency."
            ),
            PastPaperTopicEntity(
                subject = "Physics",
                chapter = "Laws of Motion & Work Energy Power",
                avgQuestionsPerYear = 4.5f,
                weightagePercentage = 5.0f,
                weightageTier = "Critical High-Yield",
                userAccuracyPercent = 70,
                totalMistakesLogged = 2,
                recommendedActions = "Solve 40 numericals on Pulley-block with friction and vertical circle tension."
            ),
            PastPaperTopicEntity(
                subject = "Physics",
                chapter = "Current Electricity & Magnetism",
                avgQuestionsPerYear = 6.0f,
                weightagePercentage = 6.6f,
                weightageTier = "Critical High-Yield",
                userAccuracyPercent = 74,
                totalMistakesLogged = 1,
                recommendedActions = "Master Kirchhoff's laws and Biot-Savart circular coils."
            )
        )
    }
}
