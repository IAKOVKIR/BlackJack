

CREATE TABLE `test` (
  `TestID` int(11) NOT NULL,
  `TestValue` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`TestID`, `TestValue`) VALUES
(1, 'Hello'),
(2, 'Hell');

-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE `user_table` (
  `userid` int(11) NOT NULL,
  `firstname` varchar(35) NOT NULL,
  `lastname` varchar(35) NOT NULL,
  `username` varchar(40) NOT NULL,
  `user_pass` varchar(35) NOT NULL,
  `lang` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`userid`, `firstname`, `lastname`, `username`, `user_pass`, `lang`) VALUES
(1, 'Kirill', 'Iakovlev', 'iakov', '12345678', 'eng'),
(2, 'Mark', 'Twain', 'Mark', '12345678', 'nor'),
(3, 'Kirill', 'Iakovlev', 'iakov1', '12345678', 'eng');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`TestID`);

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`userid`),
  ADD UNIQUE KEY `user_table_username_uindex` (`username`),
  ADD UNIQUE KEY `user_table_userid_uindex` (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `TestID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;