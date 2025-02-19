import React from "react";
import RepeatIcon from "@mui/icons-material/Repeat";
import { Avatar, Button, Menu, MenuItem } from "@mui/material";
import { useNavigate } from "react-router-dom";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";

const TweetCard = () => {
  const navigate = useNavigate();

  const [anchorEl, setAnchorEl] = React.useState(null);

    const open = Boolean(anchorEl);

    const handleClick = (event) => {
      setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
      setAnchorEl(null);
    };

    const handleDeleteTweet= ()=>{
      console.log("delete tweet");
      handleClose();
    }

  return (
    <div className="">
      {/* <div className='flex items-center font-semibold text-gray-700 py-2'>
 
            <RepeatIcon/>
            <p>You Retweet</p>
        </div> */}
      <div className="flex space-x-5">
        <Avatar
          onClick={() => navigate(`/profile/${6}`)}
          className="cursor-pointer"
          alt="username"
          src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
        />

        <div className="w-full">

          <div className="flex justify-between items-center">

            <div className="flex cursor-pointer items-center spacce-x-2">

              <span className="font-semibold">Soni Ayush v</span>
              <span className="text-gray-600">@ayush1234 . 2m</span>

            </div>

            <div className="">

              <Button
                id="basic-button"
                aria-controls={open ? "basic-menu" : undefined}
                aria-haspopup="true"
                aria-expanded={open ? "true" : undefined}
                onClick={handleClick}
              >

                <MoreHorizIcon />

              </Button>

              <Menu
                id="basic-menu"
                anchorEl={anchorEl}
                open={open}
                onClose={handleClose}
                MenuListProps={{
                  "aria-labelledby": "basic-button",
                }}
              >

                <MenuItem onClick={handleDeleteTweet}>Delete</MenuItem>
                <MenuItem onClick={handleDeleteTweet}>Edit</MenuItem>

              </Menu>

            </div>

          </div>
          
          <div className="mt-2">

            <div className="cursor-pointer">
                <p className="mb-2 p-0"> Hello I am Ayush and this is my social media project</p>
                <img  className="w-[28rem] border border-gray-400 p-5 rounded-md" src="https://ih1.redbubble.net/image.1014595711.0869/fposter,small,wall_texture,square_product,600x600.jpg" alt="" />
            </div>

          </div>
        </div>

      </div>

    </div>

  ); 
};

export default TweetCard;
