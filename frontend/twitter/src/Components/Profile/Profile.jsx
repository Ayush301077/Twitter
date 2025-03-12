import React, { useState } from "react";
import KeyboardBackspaceIcon from "@mui/icons-material/KeyboardBackspace";
import { useNavigate } from "react-router-dom";
import { Avatar, Box, Button } from "@mui/material";
import BusinessCenterIcon from '@mui/icons-material/BusinessCenter';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab'; 
import { TabContext, TabList, TabPanel } from "@mui/lab";
import TweetCard from "../HomeSection/TweetCard";
import ProfileModal from "./ProfileModal";
const Profile = () => {
  const [tabValue, setTabValue] = useState("1");
  const navigate = useNavigate();

  const [openProfileModal, setOpenProfileModal] = useState(false);
  const handleOpenProfileModel = () => setOpenProfileModal(true);
  const handleClose = () => setOpenProfileModal(false);

  const handleBack = () => {
    navigate(-1);
  };
 

  const handleFollowUser = () => {
    console.log("follow user");
  };


  const handleTabChange = (event, newValue) => {
    setTabValue(newValue)

    if(newValue === 4){
      console.log("likes tweets")
    }
    else if (newValue == 1){
      console.log("user tweets")
    }
  }
  return (
    <div>
      <section className={`bg-white z-50 flex items-center sticky top-0 bg-opacity-95`}>
        <KeyboardBackspaceIcon
          className="cursor-pointer"
          onClick={handleBack}
        />
        <h1 className="py-5 text-xl font-bold opacity-90 ml-5">Ayush Soni</h1>
      </section>
      <section>
        <img
          className="w-[100%] h-[15rem] object-cover"
          src="https://wallpapers.com/images/hd/4k-bmw-car-in-dark-c0ot64ri2fecu1pr.jpg"
          alt=""
        />
      </section>

      <section className="pl-6">
        <div className="flex justify-between items-start mt-5 h-[5rem]">
          <Avatar
            className="transform -translate-y-24"
            alt="Ayush Soni"
            src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
            sx={{ width: "10rem", height: "10rem", border: "4px solid white" }}
          />
          {true ? (
            <Button
              onClick={handleOpenProfileModel}
              variant="contained"
              sx={{ borderRadius: "20px" }}
            >
              Edit Profile
            </Button>
          ) : (
            <Button
              onClick={handleFollowUser}
              variant="contained"
              sx={{ borderRadius: "20px" }}
            >
              {true ? 'Follow' : 'Unfollow'}
            </Button>
          )}
        </div>

        <div>
          <div className="flex items-center">
            <h1 className="font-bold text-lg">Ayush soni</h1>
          </div>
          <h1 className="text-gray-500">@ayushsoni</h1>
        </div>
        <div className="mt-2 space-y-3">
          <p>Hello, I am Ayush Soni, i am studying in DDIT college</p>
          <div className="py-1 flex space-x-5">
            <div className="flex items-center text-gray-500">
              <BusinessCenterIcon/>
              <p className="ml-2">Education</p>
            </div>

            <div className="flex items-center text-gray-500">
              <LocationOnIcon/>
              <p className="ml-2">India</p>
            </div>
            
            <div className="flex items-center text-gray-500">
              <CalendarMonthIcon/>
              <p className="ml-2">Joined Mar 2025</p>
            </div>
          </div>
          <div className="flex items-center space-x-5">
            
          <div className="flex ic space-x-1 font-semibold">
              <span>700</span>
              <span className="text-gray-500">Following</span>
            </div>
            
            <div className="flex ic space-x-1 font-semibold">
              <span>600</span>
              <span className="text-gray-500">Followers</span>
            </div>
          </div>
        </div>
      </section>
      <section className="py-5">
      <Box sx={{ width: '100%', typography: 'body1' }}>
      <TabContext value={tabValue}>
        <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
          <TabList onChange={handleTabChange} aria-label="lab API tabs example">
            <Tab label="Tweets" value="1" />
            <Tab label="Replies" value="2" />
            <Tab label="Media" value="3" />
            <Tab label="Likes" value="4" />
          </TabList>
        </Box>
        <TabPanel value="1">
          {[1,1,1,1,1,1].map((item) => <TweetCard/>)}
        </TabPanel>
        <TabPanel value="2">Replies</TabPanel>
        <TabPanel value="3">Media</TabPanel>
        <TabPanel value="3">Likes</TabPanel>
      </TabContext>
    </Box>
      </section>
      <section>
        <ProfileModal handleClose={handleClose} open={openProfileModal}/>
      </section>
    </div>
  );
};

export default Profile;
